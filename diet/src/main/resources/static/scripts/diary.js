function updateSummary(summaryData) {
    // summaryData가 정의되어 있지 않은 경우 방어코드 추가
    if (!summaryData) {
        console.error('summaryData is undefined.');
        return;
    }

    // 'carbs', 'protein', 'fat' 속성이 정의되어 있는지 확인
    if ('carbs' in summaryData && 'protein' in summaryData && 'fat' in summaryData) {
        // 속성이 정의되어 있으면 값을 가져와 사용
        var updatedCarbs = summaryData.carbs;
        var updatedProtein = summaryData.protein;
        var updatedFat = summaryData.fat;

        // 섭취 정보 업데이트
        $('#width-text1').text(updatedCarbs + 'g / ' + updatedCarbs + 'g');
        $('#width-text2').text(updatedProtein + 'g / ' + updatedProtein + 'g');
        $('#width-text3').text(updatedFat + 'g / ' + updatedFat + 'g');

        // ... 추가 필요한 업데이트 코드
    } else {
        console.error('One or more attributes are missing in summaryData.');
    }
}