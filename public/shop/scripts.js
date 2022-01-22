$(document).ready(function() {

  let username = localStorage.getItem("username");
  console.log("Username: " + username)

  $("#logout").click(function (){
    $.ajax({
      type: "GET",
      url: `/dsaApp/user/logout/${username}`,
      contentType: "application/json",
      success: function (data) {
        console.log('User logged out');
        document.location.href = document.location.origin + '/'

      },
      error: function (xhr, ajaxOptions, thrownError) {
        console.log('Could not log out')
      },
    });
  })


  $.ajax({
    type: "GET",
    url: `/dsaApp/planes/getListPlanesPlayer/${username}`,
    contentType: "application/json",
    success: function (data) {
      console.log(data);
      $.each(json, function(index, element){
        if (element.model == "Airbus"){
            $("#airbus").hide();
        }
        if (element.model == "Fighter"){
            $("#fighter").hide();
        }
        if (element.model == "Cessna"){
            $("#cessna").hide();
        }
        if (element.model == "Helicopter"){
            $("#helicopter").hide();
        }
        if (element.model == "Acrobatic"){
            $("#acrobatic").hide();
        }
      });
    },
    error: function (xhr, ajaxOptions, thrownError) {
      console.log('Could not load distance ranking')
    },
  });

});