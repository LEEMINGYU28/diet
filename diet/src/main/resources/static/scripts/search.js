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
            modalContent.text(`음식 이름: ${foodInfo.foodName}, 브랜드: ${foodInfo.brand}, 칼로리: ${foodInfo.calorie}, 단백질: ${foodInfo.protein}, 탄수화물: ${foodInfo.carbohydrate}, 지방: ${foodInfo.fat}`);
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