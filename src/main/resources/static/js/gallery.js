// ------------------------------------------------------------------- write
let galfrm = document.forms.galfrm;
let title = document.querySelector("#title");
let content = document.querySelector("#content");
let go2board = document.querySelector("#go2board");
let writebtn = document.querySelector("#writebtn");

// 이미지 첨부 조건 검사
const checkAttachs = () => {

}

writebtn?.addEventListener("click",()=>{
    if(galfrm.title.value==''){ alert("제목을 입력해주세요!") }
    else if(galfrm.content.value==''){ alert("본문을 입력해주세요!") }
    else if(!checkAttachs()){ alert("이미지 첨부 조건 불일치!!") }
    else if(grecaptcha.getResponse() == '') alert('자동가입방지를 확인해주세요!');
    else {
        galfrm.method = "post";
        galfrm.enctype = "multipart/form-data";
        galfrm.submit();
    }
})

go2board?.addEventListener("click",()=>{
    location.href = "../../gallery/list";
})
// ------------------------------------------------------------------- list
let newbtn = document.querySelector("#newbtn");
newbtn?.addEventListener("click",()=>{
    location.href = "../../gallery/write";
})
// ------------------------------------------------------------------- view
listbtn?.addEventListener("click",()=>{
    location.href = "../../gallery/list";
})