Element.getById("fileIndex").addEventListener("click", function(){
    fileEvent.indexClick();
});


const fileEvent = {
    indexClick: function() {
        alert("hi");
    }
  , Add: function(element) {
        const fileWrapper = Element.getById("fileWrapper");
        const fileList = element.target.files;
        for(let i=0, len=fileList.length; i<len; i++){
            if(duplicateCheck(fileList[i])){
                const fileContainer = Element.createUl();
                Element.addClass(fileContainer, "file-info-wrapper");

                const checkBoxContainer = Element.createLi();
                const checkBoxElement = Element.createInput();
                      checkBoxElement.type="checkbox";
                      checkBoxContainer.append(checkBoxElement);

                const nameElement = Element.createLi();
                      nameElement.innerHTML = fileList[i].name;

                const sizeElement = Element.createLi();
                      sizeElement.innerHTML = fileList[i].size;

                fileContainer.append(checkBoxContainer);
                fileContainer.append(nameElement);
                fileContainer.append(sizeElement);

                Event.add(fileContainer, "click", fileEvent.Click);

                fileWrapper.append(fileContainer);

            } else {
                alert("중복 파일입니다.")
            }
        }
    }
  , Delete: function() {
        console.log(Element.getById("fileAddInput").files);
    }
  , Transfer: function() {
        Transfer.multipartRequest("/files", "POST", Element.getById("fileAddInput").files);
    }
  , Click: function(event) {
    console.log(event);
    console.log(Element.getInputInElement(event))
//        Element.getByInputTag(element)
    }
}

Event.add(Element.getById("fileAddInput"), "change", fileEvent.Add);
Event.add(Element.getById("fileDelete"), "click", fileEvent.Delete);
Event.add(Element.getById("fileTransfer"), "click", fileEvent.Transfer);


function duplicateCheck(addFileList){
    const fileArray = Element.getByClass("file-info-wrapper");
    for(let i=0, len=fileArray.length; i<len; i++){
        const fileName = Element.getChildren(fileArray[i], 1).innerHTML;
        const fileSize = Element.getChildren(fileArray[i], 2).innerHTML;
        if(addFileList.name === fileName && addFileList.size === Number(fileSize)){
            return false;
        }
    }
    return true;
}