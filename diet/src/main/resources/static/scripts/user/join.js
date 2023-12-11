const idReg = /^[a-z0-9]{3,20}$/i;
const pwReg = /^(?=.*[a-z])(?=.*\d).{10,30}$/i;
const koreanReg = /[ㄱ-ㅎㅏ-ㅣ가-힣]/g;
const numberReg = /^\d{1,3}$/;

document.getElementById("regist-form").onsubmit = function (e) {
    // id => 3~20, 영어 숫자
    const tempName = e.target.name.value.replace(koreanReg, "aa");


    let msg = checkId(e.target);
    if (!msg) msg = checkPassword(e.target);
    if (!msg) {
        if (tempName.length < 4 || tempName.length > 20) {
            msg = "이름의 길이는 한글 기준 2~10, 영어 기준 4~20으로 맞춰주세요.";
        } else if (!numberReg.test(e.target.age.value)) {
            msg = "나이는 최대 3자리의 숫자로 입력해주세요.";
        } else if (!numberReg.test(e.target.height.value)) {
            msg = "키는 최대 3자리의 숫자로 입력해주세요.";
        } else if (!numberReg.test(e.target.weight.value)) {
            msg = "몸무게는 최대 3자리의 숫자로 입력해주세요.";
        }
    }

};