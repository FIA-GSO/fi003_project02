$(document).ready(function() {
    $("#loginBtn").click(function(){
        var host = '';
        var email = $( "#inputUsername" ).val();
        var password = $( "#inputPassword" ).val();
        axios.post(host + '/user/login', {
            "email": email,
            "password": password
        })
        .then(function (response) {
            window.location.href = "/";
        })
        .catch(function (error) {
            console.log(error);
        });
    }); 
});