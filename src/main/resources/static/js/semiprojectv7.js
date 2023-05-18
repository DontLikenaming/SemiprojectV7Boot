// header 로고 클릭 이벤트
let logo = document.querySelector("#logo");
logo.addEventListener("click", function (){
    location.href="/";
})

// --------------------------------------------------------------- template(login)
const loginfrm = document.forms.loginfrm;
const mdloginbtn = document.querySelector("#mdloginbtn");
const lgoutbtn = document.querySelector("#lgoutbtn");
const joinpgbtn = document.querySelector("#joinpgbtn");

mdloginbtn?.addEventListener("click", ()=>{
    if(loginfrm.userid.value === '') alert('아이디를 입력해주세요!');
    else if(loginfrm.passwd.value === '') alert('비밀번호를 입력해주세요!');
    else {
        loginfrm.method='post';
        loginfrm.action='/login';
        loginfrm.submit();
    }
})

lgoutbtn?.addEventListener("click", ()=>{
    location.href="/logout";
})

joinpgbtn?.addEventListener("click", ()=>{
    location.href="../../join/agree";
})