// 第一個文字樣式 <link> 標籤
var linkTag1 = document.createElement('link');
linkTag1.rel = 'preconnect';
linkTag1.href = 'https://fonts.googleapis.com';

// 第二個文字樣式 <link> 標籤
var linkTag2 = document.createElement('link');
linkTag2.rel = 'preconnect';
linkTag2.href = 'https://fonts.gstatic.com';
linkTag2.crossOrigin = 'true';

// 第三個文字樣式 <link> 標籤
var linkTag3 = document.createElement('link');
linkTag3.rel = 'stylesheet';
linkTag3.href = 'https://fonts.googleapis.com/css2?family=Alfa+Slab+One&family=BIZ+UDPGothic&family=Noto+Sans+HK:wght@300;500&display=swap';

// 第四個文字樣式 <link> 標籤
var linkTag4 = document.createElement('link');
linkTag4.rel = 'stylesheet';
linkTag4.href = 'https://fonts.googleapis.com/css2?family=Noto+Sans+HK:wght@500&family=Noto+Sans+JP:wght@700&family=Noto+Sans+TC:wght@900&display=swap';

// 第五個文字樣式 <link> 標籤
var linkTag5 = document.createElement("link");
linkTag5.rel = "stylesheet";
linkTag5.href = "https://fonts.googleapis.com/css2?family=Rock+Salt&display=swap";

// 將<link> 標籤加入到 <head> 區域中
document.head.appendChild(linkTag1);
document.head.appendChild(linkTag2);
document.head.appendChild(linkTag3);
document.head.appendChild(linkTag4);
document.head.appendChild(linkTag5);

// 活動圖片上傳
function readURL(input) {

    if (input.files && input.files[0]) {

        var imageTagID = input.getAttribute("targetID");

        var reader = new FileReader();

        reader.onload = function (e) {

            var img = document.getElementById(imageTagID);

            img.setAttribute("src", e.target.result)

        }

        reader.readAsDataURL(input.files[0]);

    }

}
// sidebar
$(document).ready(function () {
    var trigger = $('.hamburger'),
        overlay = $('.overlay'),
       isClosed = false;
  
      trigger.click(function () {
        hamburger_cross();      
      });
  
      function hamburger_cross() {
  
        if (isClosed == true) {          
          overlay.hide();
          trigger.removeClass('is-open');
          trigger.addClass('is-closed');
          isClosed = false;
        } else {   
          overlay.show();
          trigger.removeClass('is-closed');
          trigger.addClass('is-open');
          isClosed = true;
        }
    }
    
    $('[data-toggle="offcanvas"]').click(function () {
          $('#wrapper').toggleClass('toggled');
    });  
  });
  
// 會員頭像
  $(document).ready(function() {

    
    var readURL = function(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('.avatar').attr('src', e.target.result);
            }
    
            reader.readAsDataURL(input.files[0]);
        }
    }
    

    $(".file-upload").on('change', function(){
        readURL(this);
    });
});

