$(document).ready(function() {

  let username = localStorage.getItem("username");
  console.log("Username: " + username)
  if (username==null){
    document.location.href = document.location.origin + '/notFound'
  }


  $.ajax({
      type: "GET",
      url: `/dsaApp/user/getByMoney/${username}`,
      contentType: "application/json",
      success: function (data) {
        console.log(data);
        $("#price").text(`Bitcoins: ${data.score}`);
      },
      error: function (xhr, ajaxOptions, thrownError) {
        console.log('Could not load bitcoins ranking')
      },
    });

  $("#logout").click(function (){
    $.ajax({
      type: "GET",
      url: `/dsaApp/user/logout/${username}`,
      contentType: "application/json",
      success: function (data) {
        console.log('User logged out');
        localStorage.clear();
        document.location.href = document.location.origin + '/'


      },
      error: function (xhr, ajaxOptions, thrownError) {
        console.log('Could not log out')
      },
    });
  });


  $.ajax({
    type: "GET",
    url: `/dsaApp/planes/getListPlanesPlayer/${username}`,
    contentType: "application/json",
    success: function (data) {
      console.log(data);
      $.each(data, function(index, element){
        if (element.model == "Acrobatic"){
            $("#1").html('<div class="progress-bar p'+${element.enginesLife}+'" id="1"><span>'+${element.enginesLife}+'%</span></div>');
            $("#2").html('<div class="progress-bar p'+${element.velY}+'" id="1"><span>'+${element.velY}+'%</span></div>');
            $("#3").html('<div class="progress-bar p'+${element.velX}+'" id="1"><span>'+${element.velX}+'%</span></div>');
            $("#4").html('<div class="progress-bar p'+${element.fuel}+'" id="1"><span>'+${element.fuel}+'%</span></div>');
            $("#5").html('<div class="progress-bar p'+${element.gravity}+'" id="1"><span>'+${element.gravity}+'%</span></div>');
        }
      });
    },
    error: function (xhr, ajaxOptions, thrownError) {
      console.log('Could not load airplanes')
    },
  });

  $("#Robustness").click(function (){
      $.ajax({
        type: "POST",
        url: `/dsaApp/planes/addUpgradeToPlayer`,
        contentType: "application/json",
        data: JSON.stringify({modificationCode: 0, playerName: username, planeModelModel: "Acrobatic"}),
        success: function (data) {
          document.location.href = document.location + '/'
        },
        error: function (xhr, ajaxOptions, thrownError) {
          console.log('Could not log out')
        },
      });
    });$("#Maneuverability").click(function (){
             $.ajax({
               type: "POST",
               url: `/dsaApp/planes/addUpgradeToPlayer`,
               contentType: "application/json",
               data: JSON.stringify({modificationCode: 1, playerName: username, planeModelModel: "Acrobatic"}),
               success: function (data) {
                 document.location.href = document.location + '/'
               },
               error: function (xhr, ajaxOptions, thrownError) {
                 console.log('Could not log out')
               },
             });
           });
    $("#Speed").click(function (){
          $.ajax({
            type: "POST",
            url: `/dsaApp/planes/addUpgradeToPlayer`,
            contentType: "application/json",
            data: JSON.stringify({modificationCode: 2, playerName: username, planeModelModel: "Acrobatic"}),
            success: function (data) {
              document.location.href = document.location + '/'
            },
            error: function (xhr, ajaxOptions, thrownError) {
              console.log('Could not log out')
            },
          });
        });
    $("#Fuel").click(function (){
          $.ajax({
            type: "POST",
            url: `/dsaApp/planes/addUpgradeToPlayer`,
            contentType: "application/json",
            data: JSON.stringify({modificationCode: 3, playerName: username, planeModelModel: "Acrobatic"}),
            success: function (data) {
              document.location.href = document.location + '/'
            },
            error: function (xhr, ajaxOptions, thrownError) {
              console.log('Could not log out')
            },
          });
        });
    $("#Weight").click(function (){
          $.ajax({
            type: "POST",
            url: `/dsaApp/planes/addUpgradeToPlayer`,
            contentType: "application/json",
            data: JSON.stringify({modificationCode: 4, playerName: username, planeModelModel: "Acrobatic"}),
            success: function (data) {
              document.location.href = document.location + '/'
            },
            error: function (xhr, ajaxOptions, thrownError) {
              console.log('Could not log out')
            },
          });
        });


});