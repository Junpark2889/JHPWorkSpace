var fileTemplate = Handlebars.compile($("#fileTemplate").html());

// 첨부파일 drag & drop 영역 선택자
var fileDropDiv = $(".fileDrop");

// 전체 페이지 첨부파일 drag & drop 기본 이벤트 방지
$(".content-wrapper").on("dragenter dragover drop", function (event) {
    event.preventDefault();
});

// 첨부파일 영역 drag & drop 기본 이벤트 방지
fileDropDiv.on("dragenter dragover", function (event) {
    event.preventDefault();
});

// 첨부파일 drag & drop 이벤트 처리 : 파일업로드 처리 -> 파일 출력
fileDropDiv.on("drop", function (event) {
	console.log("드롭이벤트발생");
    event.preventDefault();
    // drop 이벤트 발생시 전달된 파일 데이터
    var files = event.originalEvent.dataTransfer.files;
    console.log("드롭 이벤트 시 files : " + files);
    const testFile = JSON.stringify(files);
    console.log("드롭 이벤트 시 files : " + testFile);
    // 단일 파일 데이터만을 처리하기 때문 첫번째 파일만 저장
    var file = files[0];
    // formData 객체 생성, 파일데이터 저장
    var formData = new FormData();
    formData.append("file", file);
    console.log("드롭 이벤트 시 formData :" + formData);
     const testFile2 = JSON.stringify(formData);
         console.log("드롭 이벤트 시 formData :" + testFile2);
    // 파일 업로드 AJAX 통신 메서드 호출
    uploadFile(formData);
});

// 파일 업로드 AJAX 통신
function uploadFile(formData) {
    $.ajax({
        url: "/board/upload",
        data: formData,
        dataType: "text",
        // processData : 데이터를 일반적인 query string으로 변환처리할 것인지 결정
        // 기본값은 true, application/x-www-form-urlencoded 타입
        // 자동변환 처리하지 않기 위해 false
        processData: false,
        // contentType : 기본값은 true, application/x-www-form-urlencoded 타입
        // multipart/form-data 방식으로 전송하기 위해 false
        contentType: false,
        type: "POST",
        success: function (data) {
            printFiles(data); // 첨부파일 출력 메서드 호출
            $(".noAttach").remove();
        }, 
        error: function(data) {
			alert("ajax실패");
}
    });
}

// 첨부파일 출력
function printFiles(data) {
    // 파일 정보 처리
    var fileInfo = getFileInfo(data);
    console.log("fileInfo : " + fileInfo.imgSrc);
    // Handlebars 파일 템플릿에 파일 정보들을 바인딩하고 HTML 생성
    var html = fileTemplate(fileInfo);
     console.log("html : " + html);
    // Handlebars 파일 템플릿 컴파일을 통해 생성된 HTML을 DOM에 주입
    $(".uploadedFileList").append(html);
    // 이미지 파일인 경우 파일 템플릿에 lightbox 속성 추가
    if (fileInfo.fullName.substr(12, 2) === "s_") {
        // 마지막에 추가된 첨부파일 템플릿 선택자
        var that = $(".uploadedFileList li").last();
        // lightbox 속성 추가
        that.find(".mailbox-attachment-name").attr("data-lightbox", "uploadImages");
        // 파일 아이콘에서 이미지 아이콘으로 변경
        that.find(".fa-paperclip").attr("class", "fa fa-camera");
        console.log("that : " + that);
    }
}

// 게시글 입력/수정 submit 처리시에 첨부파일 정보도 함께 처리
function filesSubmit(that) {
	console.log("filesSubmit 실행했다!");
    var str = "";
    $(".uploadedFileList .delBtn").each(function (index) {
        str += "<input type='hidden' name='files[" + index + "]' value='" +$(this).attr("href")+ "'>"
    });
// $(this).attr("href")   
    that.append(str);
    that.get(0).submit();
}

// 파일 삭제(입력페이지) : 첨부파일만 삭제처리
function deleteFileWrtPage(that) {
    var url = "/board/delete";
    deleteFile(url, that);
}

// 파일 삭제 AJAX 통신
function deleteFile(url, that) {
    $.ajax({
        url: url,
        type: "post",
        data: {fileName: that.attr("href")},
        dataType: "text",
        success: function (result) {
            if (result === "DELETED") {
                alert("삭제되었습니다.");
                that.parents("li").remove();
            }
        }
    });
}

// 파일 정보 처리
function getFileInfo(fullName) {
	console.log("getFileInfo 실행했니?");
    var originalFileName;   // 화면에 출력할 파일명
    var imgSrc;             // 썸네일 or 파일아이콘 이미지 파일 출력 요청 URL
    var originalFileUrl;    // 원본파일 요청 URL
    var uuidFileName;       // 날짜경로를 제외한 나머지 파일명 (UUID_파일명.확장자)
    

    // 이미지 파일이면
    if (checkImageType(fullName)) {
        imgSrc = "/board/display?fileName=" + fullName; // 썸네일 이미지 링크
        uuidFileName = fullName.substr(14);
        console.log("자바스크립트의 uuidFileName : " + uuidFileName);
        var originalImg = fullName.substr(0, 12) + fullName.substr(14);
        // 원본 이미지 요청 링크
        originalFileUrl = "/board/display?fileName=" + originalImg;
    } else {
        imgSrc = "/upload/files/file-icon.svg"; // 파일 아이콘 이미지 링크
        uuidFileName = fullName.substr(12);
        // 파일 다운로드 요청 링크
        originalFileUrl = "/board/display?fileName=" + fullName;
        
  
    }
    originalFileName = uuidFileName.substr(uuidFileName.indexOf("_") + 1);
//	var testUp = {originalFileName: originalFileName, imgSrc: imgSrc, originalFileUrl: originalFileUrl, fullName: fullName};
//	const testUp =  {"originalFileName": originalFileName, "imgSrc": imgSrc, "originalFileUrl": originalFileUrl, "fullName": fullName};
    return {originalFileName: originalFileName, imgSrc: imgSrc, originalFileUrl: originalFileUrl, fullName: fullName};
//	return objj;
}

// 이미지 파일 유무 확인
function checkImageType(fullName) {
    var pattern = /.jpg$|.gif$|.png$|.jpeg$/i;
    return fullName.match(pattern);
}

// 게시글 저장 버튼 클릭 이벤트 처리
//$("#writeForm2").submit(function (event) {
//    event.preventDefault();
//    var that = $(this);
//    filesSubmit(that);
//});
//// 수정 처리시 첨부파일 정보도 함께 처리
//$("#modifyForm").submit(function (event) {
//    event.preventDefault();
//    var that = $(this);
//    filesSubmit(that);
//});




// 파일 삭제 버튼 클릭 이벤트
$(document).on("click", ".delBtn", function (event) {
    event.preventDefault();
    var that = $(this);
    deleteFileWrtPage(that);
});

// 파일 목록 : 게시글 조회, 수정페이지
function getFiles(qnaNo) {
   $.getJSON("/board/fileList/" + qnaNo, function (fileList) {
	   console.log("fileList 에 몇개 있음?: " +fileList.length);
       if (fileList.length === 0) {
 			
           $(".uploadedFileList").html("<span class='noAttach'>첨부파일이 없습니다.</span>");
       }
       $(fileList).each(function () {
		    const test = JSON.stringify(this);
		    const obje = JSON.parse(test);
			console.log("fullName : " + obje.oriFileName);
           printFiles(obje.oriFileName);
           
       })
   });
}
////// 현재 게시글 번호
//var qnaNo = "[[${boardFile}]]";
//// 첨부파일 목록
//getFiles(qnaNo);

// 게시글 삭제 클릭 이벤트
$("#delButton").on("click", function () {
//
//    // 댓글이 달린 게시글 삭제처리 방지
    var replyCnt = $(".replyDiv").length;
   if (replyCnt > 0) {
        alert("댓글이 달린 게시글은 삭제할수 없습니다.");
      return;
    }
//
//    // 첨부파일명들을 배열에 저장
    var arr = [];
    $(".uploadedFileList li").each(function () {
        arr.push($(this).attr("data-src"));
   });
    console.log("arr 에 들어있는 값 :" + arr);

//    // 첨부파일 삭제요청
    if (arr.length > 0) {
       $.post("/board/deleteAll", {files: arr}, function () {

       });
    }
//
//    // 삭제처리
////    formObj.attr("action", "/board/delete" + boardNum);
////    formObj.submit();
});
// 현재 게시글 번호
//var qnaNo = "[[${board.id}]]";

// 첨부파일 목록
