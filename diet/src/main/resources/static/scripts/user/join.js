document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('regist-form').addEventListener('submit', function (event) {
        event.preventDefault();

        var gender = document.querySelector('input[name="gender"]:checked');
        var weight = parseFloat(document.getElementById('weight').value);
        var height = parseFloat(document.getElementById('height').value);
        var age = parseFloat(document.getElementById('age').value);

        console.log('Gender:', gender ? gender.value : null);
        console.log('Weight:', weight);
        console.log('Height:', height);
        console.log('Age:', age);

        // debugger;
        var Kcal, fat, protein, carbs;
        if (gender && weight && height && age) {
            if (gender.value === '1') { // 남성
                Kcal = Math.floor(66.47 + (13.75 * weight) + (5 * height) - (6.76 * age));
            } else if (gender.value === '2') { // 여성
                Kcal = Math.floor(655.1 + (9.56 * weight) + (1.85 * height) - (4.68 * age));
            }

            // 비율 및 규칙에 따른 지방, 단백질, 탄수화물 계산
            var fatRatio = 0.20; // 20% 지방
            var proteinPerKg = 1.5; // 체중 당 1.5g 단백질
            var carbsRatio = 1 - fatRatio;

            fat = Math.floor((fatRatio * Kcal) / 9); // 20% 지방을 originalKcal에 나누기 9
            protein = Math.floor(proteinPerKg * weight); // 1.5g 단백질 * 체중(kg)
            carbs = Math.floor((Kcal - (fat * 9 + protein * 4)) / 4); // 남은 originalKcal을 탄수화물로 나누기 (1g 탄수화물 = 4kcal)

            console.log('Original Kcal:', Kcal);
            console.log('Calculated Fat (g):', fat);
            console.log('Calculated Protein (g):', protein);
            console.log('Calculated Carbs (g):', carbs);

            // Original Kcal 값을 폼 데이터에 추가
            var originalKcalInput = document.createElement('input');
            originalKcalInput.type = 'hidden';
            originalKcalInput.name = 'kcal';
            originalKcalInput.value = Kcal;
            this.appendChild(originalKcalInput);

            // 계산된 값들을 폼 데이터에 추가
            var fatInput = document.createElement('input');
            fatInput.type = 'hidden';
            fatInput.name = 'fat';
            fatInput.value = fat;
            this.appendChild(fatInput);

            var proteinInput = document.createElement('input');
            proteinInput.type = 'hidden';
            proteinInput.name = 'protein';
            proteinInput.value = protein;
            this.appendChild(proteinInput);

            var carbsInput = document.createElement('input');
            carbsInput.type = 'hidden';
            carbsInput.name = 'carbs';
            carbsInput.value = carbs;
            this.appendChild(carbsInput);
        }

        // 폼 제출
        this.submit();
    });
});