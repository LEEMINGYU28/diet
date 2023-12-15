var searchResults = [];

function performSearch() {
    var searchTerm = document.querySelector('.search-txt').value;

    if (searchTerm.trim() !== '') {
        $.ajax({
            type: 'GET',
            url: '/searchs',
            data: { keyword: searchTerm },
            dataType: 'json',
            success: function (data) {
                searchResults = Array.isArray(data) ? data : [];
                displaySearchResults(searchResults);
                initializeSearchResults(searchResults.length);
            },
            error: function (xhr, status, error) {
                console.error('검색에 실패하였습니다. 상태 코드:', xhr.status);
                console.error('에러 메시지:', error);
                alert('검색에 실패하였습니다.');
            }
        });
    } else {
        // 검색어가 비어있을 경우 처리
    }
}


function displaySearchResults(data) {
    var resultsContainer = $('#searchResults');
    resultsContainer.empty(); // 기존 결과 지우기

    if (data.length > 0) {
        var list = $('<ul></ul>');

        $.each(data, function (index, food) {
            var listItem = $('<li></li>').text(food.foodName);
            listItem.click(function () {
                openModal(food.foodName, food.otherData, food.brand);
            });
            list.append(listItem);
        });

        resultsContainer.append(list);
    } else {
        resultsContainer.text('검색 결과가 없습니다.');
    }
}
function showResultsForPage(pageNumber, pageSize, totalResults) {
    const startIndex = (pageNumber - 1) * pageSize;
    const endIndex = startIndex + pageSize;
    const resultsContainer = $('#searchResults');

    const paginatedResults = searchResults.slice(startIndex, endIndex);


    resultsContainer.empty();


    var list = $('<ul></ul>');
    $.each(paginatedResults, function (index, food) {
        var listItem = $('<li></li>').text(food.foodName);
        list.append(listItem);
    });
    resultsContainer.append(list);


    updatePaginationControls(pageNumber, pageSize, totalResults);
}


function updatePaginationControls(currentPage, pageSize, totalResults) {
    const totalPages = Math.ceil(totalResults / pageSize);
    const paginationControls = $('#paginationControls');
    paginationControls.empty();


    const pagesPerGroup = 10;

    const startPage = Math.floor((currentPage - 1) / pagesPerGroup) * pagesPerGroup + 1;

    const endPage = Math.min(startPage + pagesPerGroup - 1, totalPages);

    for (let i = startPage; i <= endPage; i++) {
        const li = $('<li></li>');
        const link = $('<a></a>');
        link.attr('href', '');
        link.text(i);
        link.click(function () {
            showResultsForPage(i, pageSize, totalResults);
        });
        li.append(link);
        paginationControls.append(li);
    }
}


function initializeSearchResults() {
    const totalResults = searchResults.length;
    const pageSize = 10;
    const initialPage = 1;

    showResultsForPage(initialPage, pageSize, totalResults);
}


initializeSearchResults(searchResults.length);

function openModal(foodName, otherData, brand) {
    // 모달 내용 설정
    var modalTitle = $('#modalTitle');
    var modalContent = $('#modalContent');

    // foodName으로 전체 정보를 가져오는 AJAX 요청 추가
    $.ajax({
        type: 'GET',
        url: '/getFoodInfo',
        data: { foodName: foodName },
        dataType: 'json',
        success: function (foodInfo) {
            // 가져온 정보를 모달에 표시
            modalTitle.text(foodInfo.brand);
            modalContent.html(`
                <dl>
                    <dt>음식 이름:</dt>${foodInfo.foodName}
                    <dt>브랜드:</dt>${foodInfo.brand}
                    <dt>칼로리:</dt>${foodInfo.calorie}
                    <dt>탄수화물:</dt>${foodInfo.carbohydrate}
                    <dt>단백질:</dt>${foodInfo.protein}
                    <dt>지방:</dt>${foodInfo.fat}
                </dl>
            `);

            // 추가하기 버튼 설정
            var addButton = $('<button></button>').text('추가하기');
            addButton.attr('id', 'addFoodButton');
            addButton.click(function () {
                var selectedMealType = getMealTypeFromUser(); // 사용자로부터 mealType을 어떻게 받을지 함수 호출을 추가
                addFoodToMeal(foodInfo.foodName, foodInfo.brand, foodInfo.calorie, foodInfo.carbohydrate, foodInfo.protein, foodInfo.fat, selectedMealType);
            });

            // 모달에 추가 버튼 추가
            modalContent.append(addButton);

            // 모달 표시
            $('#itemModal').css('display', 'block');
        },
        error: function (xhr, status, error) {
            console.error('음식 정보 가져오기에 실패하였습니다. 상태 코드:', xhr.status);
            console.error('에러 메시지:', error);
            alert('음식 정보 가져오기에 실패하였습니다.');
        }
    });
}

// 리스트 클릭 시 모달 열기
$('#searchResults').on('click', 'li', function () {
    var foodName = $(this).text();
    openModal(foodName);
});

// 모달 닫기
function closeModal() {
    $('#itemModal').css('display', 'none');
}
function getMealTypeFromUser() {

    return 1;
}



function addFoodToMeal(foodName, brand, calorie, carbohydrate, protein, fat, mealType) {

    var userInput = prompt('추가할 식사 종류를 선택하세요.\n1: 아침, 2: 점심, 3: 저녁, 4: 간식', '1');


    if (userInput === null || isNaN(userInput)) {
        mealType = 1;
    } else {

        mealType = parseInt(userInput, 10);
    }

    $.ajax({
        type: 'POST',
        url: '/addFoodToMeal',
        data: {
            foodName: foodName,
            brand: brand,
            calorie: calorie,
            carbohydrate: carbohydrate,
            protein: protein,
            fat: fat,
            mealType: mealType
        },
        success: function (data) {
            closeModal();
            alert('음식이 ' + getMealTypeName(mealType) + '에 추가되었습니다.');
        },
        error: function (xhr, status, error) {
            console.error('음식 추가에 실패했습니다. 상태 코드:', xhr.status);
            console.error('에러 메시지:', error);
            alert('음식 추가에 실패했습니다.');
        }
    });
}
function getMealTypeName(mealType) {
    switch (mealType) {
        case 1:
            return '아침';
        case 2:
            return '점심';
        case 3:
            return '저녁';
        case 4:
            return '간식';
        default:
            return '알 수 없음';
    }
}