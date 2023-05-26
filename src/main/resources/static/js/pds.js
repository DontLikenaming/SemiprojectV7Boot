// ------------------------------------------------------------------- write
let pdsfrm = document.forms.pdsfrm;
let title = document.querySelector("#title");
let content = document.querySelector("#content");
let go2board = document.querySelector("#go2board");
let writebtn = document.querySelector("#writebtn");

writebtn?.addEventListener("click",()=>{
    if(pdsfrm.title.value==''){ alert("제목을 입력해주세요!") }
    else if(pdsfrm.content.value==''){ alert("본문을 입력해주세요!") }
    else if(grecaptcha.getResponse() == '') alert('자동가입방지를 확인해주세요!');
    else {
        pdsfrm.method = "post";
        pdsfrm.enctype = "multipart/form-data";
        pdsfrm.submit();
    }
})

go2board?.addEventListener("click",()=>{
    location.href = "../../pds/list";
})
// ------------------------------------------------------------------- list
let newbtn = document.querySelector("#newbtn");
newbtn?.addEventListener("click",()=>{
    location.href = "../../pds/write";
})
// ------------------------------------------------------------------- view
listbtn?.addEventListener("click",()=>{
    location.href = "../../pds/list";
})
let rpfrm = document.forms.rpfrm;
let rpnewbtn = document.querySelector("#rpnewbtn");
rpnewbtn?.addEventListener("click",()=>{
    if(rpfrm.reply.value==''){ alert("댓글을 작성해주세요!") }
    else if(rpfrm.userid.value==''){ alert("본문을 입력해주세요!") }
    else if(rpfrm.pno.value==''){ alert("번호 매칭 에러!") }
    else {
        rpfrm.method = "post";
        rpfrm.action = "/pds/replyok";
        rpfrm.submit();
    }
})