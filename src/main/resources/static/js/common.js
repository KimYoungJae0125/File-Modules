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
        ajax: function(requestUrl, httpMethod){
            const xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function () {
                if(xhr.readyState === XMLHttpRequest.DONE) {
                    if(xhr.status === 200 || xhr.status === 201) {
                    }
                }
            }

            xhr.open(httpMethod, requestUrl);
            xhr.send();
        }
}