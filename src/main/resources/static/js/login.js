const sliderContainer = document.querySelector('.image-slider');
const slides = sliderContainer.querySelectorAll('img');
let currentIndex = 0;

slides[currentIndex].style.display = 'block';

function startSlideshow() {
    setInterval(function() {

        slides[currentIndex].style.display = 'none';
        currentIndex = (currentIndex + 1) % slides.length;

        slides[currentIndex].style.display = 'block';
    }, 3000);
}
startSlideshow();

function openModal() {
    document.getElementById('xgModal').style.display = 'block';
}

function closeModal() {
    document.getElementById('xgModal').style.display = 'none';
}

//登录信息封装
$(document).ready(function() {
    // Register form submit event
    $("#load-form").submit(function(event) {
        event.preventDefault(); // Prevent the default form submit behavior

        var username = $("#username").val();
        var password = $("#password").val();
        sessionStorage.setItem('username', username);

        // Use Ajax to send login information to the back-end
        $.ajax({
            url: "/login/login", // Replace with the correct server-side URL for login
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                "loginName": username,
                "password": password
            }),
            success: function(response) {
                debugger
                console.log(response+"-------------------")
                if (response.code === "0000") {
                    alert(response.msg);
                    window.location.href = "html/index.html";
                } else if(response.code === "9999"){
                    alert(response.msg);
                    // document.getElementById('xgModal').style.display = 'block';
                }else{
                    alert(response.message)
                }
            },
            error: function(xhr, status, error) {
                alert("登录过程中发生错误: " + error);
            }
        });
    });
});
