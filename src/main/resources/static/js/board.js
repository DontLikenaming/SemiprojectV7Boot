// ------------------------------------------------------------------- list
let findtype = document.querySelector("#findtype");
let findkey = document.querySelector("#findkey");
let findbtn = document.querySelector("#findbtn");

findbtn?.addEventListener("click",()=>{
    if(findkey.value==''){alert("검색어를 입력하세요!"); return null;}

    let query = `/board/find?ftype=${findtype.value}&fkey=${findkey.value}&cpg=1`;

    location.href = query;
})

let newbtn = document.querySelector("#newbtn");
newbtn?.addEventListener("click",()=>{
    location.href = "../../board/write";
})

let bdfrm = document.forms.bdfrm;
let go2board = document.querySelector("#go2board");
let writebtn = document.querySelector("#writebtn");
let cancelbtn = document.querySelector("#cancelbtn");

go2board?.addEventListener("click",()=>{
    location.href = "../../board/list?cpg=1";
})

cancelbtn?.addEventListener("click",()=>{
    location.href = "../../board/list?cpg=1";
})

writebtn?.addEventListener("click",()=>{
    if(bdfrm.title.value==''){ alert("제목을 입력해주세요!") }
    else if(bdfrm.content.value==''){ alert("본문을 입력해주세요!") }
    else if(grecaptcha.getResponse()==''){ alert("자동 가입 방지를 확인해주세요!") }
    else {
        bdfrm.method = "post";
        bdfrm.submit();
    }
})

prebtn = document.querySelector("#prebtn");
nextbtn = document.querySelector("#nextbtn");
updbtn = document.querySelector("#updbtn");
rmvbtn = document.querySelector("#rmvbtn");
listbtn = document.querySelector("#listbtn");
bno = document.querySelector("#bno");
if(bno){
    prebno = Number(bno.value)-1;
    nextbno = Number(bno.value)+1;
}


prebtn?.addEventListener("click",()=>{
    location.href = `/board/view?bno=`+prebno;
})
nextbtn?.addEventListener("click",()=>{
    location.href = `/board/view?bno=`+nextbno;
})
listbtn?.addEventListener("click",()=>{
    location.href = "../../board/list?page=1";
})