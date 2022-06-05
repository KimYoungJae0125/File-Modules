commonUtils.Element.getById("fileIndex").addEventListener("click", function(){
    fileEvent.indexClick();
});


const fileEvent = {
    indexClick: function() {
        alert("hi");
    }
  , Add: function(element) {
        const fileWrapper = commonUtils.Element.getById("fileWrapper");
        const fileList = element.target.files;
        for(let i=0, len=fileList.length; i<len; i++){
            if(duplicateCheck(fileList[i])){
                const fileContainer = commonUtils.Element.createUl();
                      fileContainer.classList.add("file-info-wrapper");

                const checkBoxContainer = commonUtils.Element.createLi();
                const checkBoxElement = commonUtils.Element.createInput();
                      checkBoxElement.type="checkbox";
                      checkBoxContainer.append(checkBoxElement);

                const nameElement = commonUtils.Element.createLi();
                      nameElement.innerHTML = fileList[i].name;

                const sizeElement = commonUtils.Element.createLi();
                      sizeElement.innerHTML = fileList[i].size;

                fileContainer.append(checkBoxContainer);
                fileContainer.append(nameElement);
                fileContainer.append(sizeElement);

                fileWrapper.append(fileContainer);
            } else {
                alert("중복 파일입니다.")
            }
        }
    }
  , Delete: function() {
        console.log(commonUtils.element.getById("fileAddInput").files);
  }
  , Transfer: function() {
        commonUtils.Transfer.ajax("/test", "GET");
  }
}

commonUtils.Event.add(commonUtils.Element.getById("fileAddInput"), "change", fileEvent.Add);
commonUtils.Event.add(commonUtils.Element.getById("fileDelete"), "click", fileEvent.Delete);
commonUtils.Event.add(commonUtils.Element.getById("fileTransfer"), "click", fileEvent.Transfer);


function duplicateCheck(addFileList){
    const fileArray = commonUtils.Element.getByClass("file-info-wrapper");
    for(let i=0, len=fileArray.length; i<len; i++){
        const fileName = commonUtils.Element.getChildren(fileArray[i], 1).innerHTML;
        const fileSize = commonUtils.Element.getChildren(fileArray[i], 2).innerHTML;
        if(addFileList.name === fileName && addFileList.size === Number(fileSize)){
            return false;
        }
    }
    return true;
}