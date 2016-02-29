/**
 * Created by neowyp on 2016/2/25.
 */
$(document).ready(function () {
    //alert("test");
    var treeData = [{
        "id": 1,
        "text": "Folder1",
        "iconCls": "icon-save",
        "url": "http://www.baidu.com",
        "children": [{
            "text": "File1",
            "checked": true
        }, {
            "text": "Books",
            "state": "open",
            "attributes": {
                "url": "/demo/book/abc",
                "price": 100
            },
            "children": [{
                "text": "PhotoShop",
                "checked": true
            }, {
                "id": 8,
                "text": "Sub Bookds",
                "state": "closed"
            }]
        }]
    }, {
        "text": "Languages",
        //"state": "closed",
        "children": [{
            "text": "Page01",
            "url": "Page01.html",
        }, {
            "text": "Page02",
            "url": "Page02.html",
        }]
    }];
    $('#tt').tree({
        data: treeData,
        closed: true,
        collapsible: false,
        onClick: function (node) {
            //alert(node.url);  // alert node text property when clicked
            //$('#mainFrame').src(node.url).reload();
            $('#mainFrame').attr("src", node.url);
        }
    })
})
