// ------------------------------------------------------------------- agree

// 이용약관 체크박스
let agree1 = document.querySelector("#agree1");

// 개인정보 이용 체크박스
let agree2 = document.querySelector("#agree2");

// 동의 버튼 이벤트
let agreeok = document.querySelector("#agreeok");
agreeok?.addEventListener("click", () => {
    if(!agree1.checked) alert("이용약관 동의에 체크해주세요!")
    else if(!agree2.checked) alert("개인정보 이용 동의에 체크해주세요!")
    else location.href="../../join/checkme";
})

// 미동의 버튼 이벤트
let noagree = document.querySelector("#noagree");
noagree?.addEventListener("click", () => {
    location.href="/";
})

// ------------------------------------------------------------------- checkme
const checkpnum = document.querySelector("#checkpnum");
const checkfrm2 = document.forms.checkfrm2;
const name2 = document.querySelector("#name2");
const name = document.querySelector("#name");

let checkbtn2 = document.querySelector("#checkbtn2");
checkbtn2?.addEventListener("click", () => {
if(!checkpnum.checked) alert("개인정보 처리 동의에 체크해주세요!")
    else {
        name.value = name2.value;
        checkfrm2.method = 'post';
        checkfrm2.action="/join/checkme";
        checkfrm2.submit();
    }
})


let cancelbtn2 = document.querySelector("#cancelbtn2");
cancelbtn2?.addEventListener("click", () => {
    location.href="/";
})


// ------------------------------------------------------------------- joinme
const joinfrm = document.forms.joinfrm;
const userid = document.querySelector("#userid");
const passwd = document.querySelector("#passwd");
const reppsswd = document.querySelector("#reppsswd");
const uidmsg = document.querySelector("#uidmsg");
const pwdmsg = document.querySelector("#pwdmsg");
const repwdmsg = document.querySelector("#repwdmsg");
const zipmdbtn = document.querySelector("#zipmdbtn");
const zipmodal = document.querySelector("#zipmodal");
const dong = document.querySelector("#dong");
const zipbtn = document.querySelector("#findzipbtn");
const addrlist = document.querySelector("#addrlist");
const addrclose = document.querySelector("#addrclose");
const email3 = document.querySelector("#email3");
const joinbtn = document.querySelector("#joinbtn");
let modal = null;


const styleCheckuid = (chkuid) => {
    let msg = "사용 불가능한 아이디입니다!";
    uidmsg.style.color = "red";
    joinfrm.checkuid.value = "no";

    if(parseInt(chkuid)===1){
        uidmsg.innerText = msg;
    } else if(parseInt(chkuid)===0){
        uidmsg.style.color = "blue";
        uidmsg.innerText = "사용 가능한 아이디입니다!";
        joinfrm.checkuid.value = "yes";
    }

};
userid?.addEventListener("blur", ()=>{
    let checkid = new RegExp("(?=[a-z0-9_]{6,16})(?=^((?![^a-z0-9_]).)*$)");


    if(userid.value==='') {
        uidmsg.style.color = null;
        uidmsg.innerText = "6~16 자의 영문 소문자, 숫자와 특수기호(_)만 사용할 수 있습니다.";
        joinfrm.checkuid.value = "no";
    } else if(!checkid.test(userid.value)){
        uidmsg.style.color = "red";
        uidmsg.innerText = "형식에 맞지 않습니다!";
        joinfrm.checkuid.value = "no";
    } else {
        const url = '/join/checkuid?uid=' + userid.value;
        fetch(url).then(response => response.text())
            .then(text => styleCheckuid(text));
    }
})

passwd?.addEventListener("blur", ()=>{
    let checkpwd = new RegExp("(?=[a-z0-9_]{6,16})(?=^((?![^a-z0-9_]).)*$)");
    joinfrm.checkpwd.value = 'no';

    if(passwd.value==='') {
        pwdmsg.style.color = null;
        pwdmsg.innerText = "6~16 자의 영문 소문자, 숫자와 특수기호(_)만 사용할 수 있습니다.";
    } else if(!checkpwd.test(passwd.value)){
        pwdmsg.style.color = "red";
        pwdmsg.innerText = "형식에 맞지 않습니다!";
    } else {
        pwdmsg.style.color = "blue";
        pwdmsg.innerText = "사용 가능한 비밀번호입니다!";
        joinfrm.checkpwd.value = 'yes';
    }
})

reppsswd?.addEventListener("blur", ()=>{
    if(reppsswd.value===''){
        repwdmsg.style.color = null;
        repwdmsg.innerText = "이전 항목에서 입력했던 비밀번호를 한번 더 입력해주세요.";
    } else if(reppsswd.value!==passwd.value){
        repwdmsg.style.color = "red";
        repwdmsg.innerText = "비밀번호가 일치하지 않습니다!";
    } else {
        repwdmsg.style.color = "blue";
        repwdmsg.innerText = "비밀번호가 일치합니다!";
    }
})

zipmdbtn?.addEventListener("click",()=>{
    while (addrlist.lastChild){
        addrlist.removeChild(addrlist.lastChild);
    }
    dong.value = '';
    try{
        modal = new bootstrap.Modal(zipmodal, {});
    } catch (e) {

    }
    modal.show();
})

dong?.addEventListener("keydown", (e)=>{
    if(e.keyCode === 13) {
        e.preventDefault();
        zipbtn.click();
    }
})

const showzipaddr = (jsons) => {

    /*    for(idx in jsons){
            console.log(jsons[idx]);
        }

        for(i of Object.values(jsons)) {
            console.log(i);
        }
    */

    jsons = JSON.parse(jsons);
    let addrs = '';
    jsons.forEach(function (data, idx){
        let ri = (data['ri']!==null) ? data['ri'] : '';
        let bunji = (data['bunji']!==null) ? data['bunji'] : '';

        addrs += `<option>${data['zipcode']} ${data['sido']} ${data['gugun']}
                 ${data['dong']} ${ri} ${bunji}</option>`;


    })

    while (addrlist.lastChild){
        addrlist.removeChild(addrlist.lastChild);
    }

    addrlist.innerHTML = addrs;

};
zipbtn?.addEventListener("click", () =>{
    if(dong.value==='') {
        alert("검색할 동 이름을 입력하세요!");
        return;
    }

    const url = '/join/zipcode?dong=' + dong.value;
    fetch(url).then(response => response.text())
        .then(text => showzipaddr(text));
})

addrclose?.addEventListener("click", () =>{
    let addr = addrlist.value;

    if(addr !== ''){
        let zipsplt = addr.split(' ')[0];
        let zipsplt2 = `${addr.split(' ')[1]} ${addr.split(' ')[2]} ${addr.split(' ')[3]}`;
        let zipsplt3 = '';

        let ri = addr.split(' ')[4];
        let bunji = addr.split(' ')[5];

        if((ri!==undefined)&&(bunji!==undefined)) {
            zipsplt3 = `${addr.split(' ')[4]} ${addr.split(' ')[5]}`;
        } else if((ri!==undefined)&&(bunji===undefined)){
            zipsplt3 = `${addr.split(' ')[4]}`;
        } else if((ri===undefined)&&(bunji!==undefined)){
            zipsplt3 = `${addr.split(' ')[5]}`;
        }

        joinfrm.zip1.value = zipsplt.split('-')[0];
        joinfrm.zip2.value = zipsplt.split('-')[1];
        joinfrm.addr1.value = zipsplt2;
        joinfrm.addr2.value = zipsplt3;
        joinfrm.addr2.focus();
    } else {
        alert('주소를 입력하세요!');
    }

    modal.hide();
});

email3?.addEventListener("click",() => {
    if(joinfrm.email3.value=="직접 입력하기"){
        joinfrm.email2.value = '';
        joinfrm.email2.readOnly = false;
    } else {
        joinfrm.email2.readOnly = true;
        if(joinfrm.email3.value!=="선택하세요"){
            joinfrm.email2.value=joinfrm.email3.value;
        }
    }
});

joinbtn?.addEventListener("click", () =>{
/*if(grecaptcha.getResponse() == '') alert('자동가입방지를 확인해주세요!');
    else*/ {
        joinfrm.zipcode.value = joinfrm.zip1.value + "-" + joinfrm.zip2.value;
        joinfrm.email.value = joinfrm.email1.value + "@" + joinfrm.email2.value;

        joinfrm.method='post';
        joinfrm.action='/join/joinme';
        joinfrm.submit();
    }
})

// ------------------------------------------------------------------- joinok
const go2index = document.querySelector("#go2index");
go2index?.addEventListener("click", ()=>{
    location.href = '/';
});