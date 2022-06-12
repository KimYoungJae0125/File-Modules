const Element = {
        getById: function(elementId) {
            return document.getElementById(elementId);
        }
      , getByClass: function(elementClassName) {
            return document.getElementsByClassName(elementClassName);
        }
      , getChildren: function(element, index) {
            return element.children[index];
        }
      , createUl: function() {
            return document.createElement("ul");
        }
      , createLi: function() {
            return document.createElement("li");
        }
      , createInput: function() {
            return document.createElement("input");
        }
      , createForm: function() {
            return document.createElement("form");
        }
      , addClass: function(element, className) {
            element.classList.add(className);
        }
      , getInputInElement: function(element) {
            return element.getElementsByTagName("input")
        }
      , inputCheck: function(element, index) {
            element.checked = !element.checked;
        }
}
const Event = {
        add: function(element, type, func) {
            element.addEventListener(type, func);
        }
}
const Transfer = {
        ajax: function(requestUrl, httpMethod, contentType, requestData){
            const xhr = new XMLHttpRequest();

            xhr.open(httpMethod, requestUrl);

            if(contentType != null && contentType != ""){
                xhr.setRequestHeader("Content-Type", contentType);
            }

            xhr.onreadystatechange = function () {
                if(xhr.readyState === XMLHttpRequest.DONE) {
                    if(xhr.status === 200 || xhr.status === 201) {
                    }
                }
            }

            xhr.send(requestData);
        }
      , jsonRequest: function(requestUrl, httpMethod, jsonData) {
            Transfer.ajax(requestUrl, httpMethod, "application/json", jsonData);
        }
      , multipartRequest: function(requestUrl, httpMethod, fileData) {
            const formData = new FormData();
            formData.append("file", fileData);
            formData.method="post";
            formData.enctype = "multipart/form-data";

            Transfer.ajax(requestUrl, "POST", "", formData);

        }
}