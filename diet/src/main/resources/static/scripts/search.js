function performSearch() {
    // 검색어를 가져오기
    var searchTerm = document.querySelector('.search-txt').value;

    // 검색어가 비어있지 않은 경우에만 검색 실행
    if (searchTerm.trim() !== '') {
        // AJAX를 사용하여 서버에 검색 요청 보내고, 결과를 받아오는 로직
        $.ajax({
            type: 'GET',
            url: '/searchs',
            data: { keyword: searchTerm },
            dataType: 'json',
            success: function (data) {
                // 서버에서 받아온 검색 결과를 처리하는 로직
                console.log('검색 결과:', data);
                displaySearchResults(data);
                
            },
            error: function (xhr, status, error) {
                console.error('검색에 실패하였습니다. 상태 코드:', xhr.status);
                console.error('에러 메시지:', error);
                alert('검색에 실패하였습니다. 자세한 정보는 콘솔을 확인하세요.');
            }
        });
    } else {
    }
}

function displaySearchResults(data) {
    var resultsContainer = $('#searchResults');
    resultsContainer.empty(); // 기존 결과 지우기

    if (data.length > 0) {
        var list = $('<ul></ul>');

        $.each(data, function (index, food) {
            var listItem = $('<li></li>').text(food.foodName);
            list.append(listItem);
        });

        resultsContainer.append(list);
    } else {
        resultsContainer.text('검색 결과가 없습니다.');
    }
}
