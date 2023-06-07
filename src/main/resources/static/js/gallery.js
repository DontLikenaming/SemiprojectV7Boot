// ------------------------------------------------------------------- write
let galfrm = document.forms.galfrm;
let title = document.querySelector("#title");
let content = document.querySelector("#content");
let go2board = document.querySelector("#go2board");
let writebtn = document.querySelector("#writebtn");

// 이미지 첨부 조건 검사
const checkAttachs = () => {
    let checkOK = true;
    const attachs = document.querySelector("#attachs");

    // 이미지 첨부 파일이 하나 이상 존재하면
    if('files' in attachs && attachs.files.length > 0) {
        for (attach of attachs.files) {
            //console.log(attach.name + ', ' + attach.type + ', ' + attach.size);

            // 이미지 파일의 MINE 형식 : image/png, image/jpg, image/jpeg, image/gif...

            // 첨부파일이 image 인지 검사
            if (attach.type.startsWith('image')) {

            } else {
                alert("첨부하려는 파일은 반드시 이미지여야 합니다!");
                checkOK = false;
            }
        }
    }  else {
        alert("하나 이상의 이미지를 선택해주세요!");
        checkOK = false;
    }

    return checkOK;
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