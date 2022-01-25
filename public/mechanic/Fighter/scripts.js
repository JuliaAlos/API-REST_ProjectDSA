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
    url: `/dsaApp/planes/getPlaneByModel/${username}/Fighter`,
    contentType: "application/json",
    success: function (element) {
      console.log(element);

        var a =element.enginesLife;
        var b =element.velY;
        var c =element.velX;
        var d =element.fuel;
        var e =element.gravity;
            $("#1").html('<div class="progress-bar p'+ a +'" id="1"><span>'+ a +'%</span></div>');
            $("#2").html('<div class="progress-bar p'+ b +'" id="1"><span>'+ b +'%</span></div>');
            $("#3").html('<div class="progress-bar p'+ c +'" id="1"><span>'+ c +'%</span></div>');
            $("#4").html('<div class="progress-bar p'+ d +'" id="1"><span>'+ d +'%</span></div>');
            $("#5").html('<div class="progress-bar p'+ e +'" id="1"><span>'+ e +'%</span></div>');
        if (a == 50){$("#Robustness").hide();}
        if (b == 90){$("#Maneuverability").hide();}
        if (c == 100){$("#Speed").hide();}
        if (d == 50){$("#Fuel").hide();}
        if (e == 40){$("#Weight").hide();}

    },
    error: function (xhr, ajaxOptions, thrownError) {
      console.log('Could not load airplane')
    },
  });

  $("#Robustness").click(function (){
      $.ajax({
        type: "POST",
        url: `/dsaApp/planes/addUpgradeToPlayer`,
        contentType: "application/json",
        data: JSON.stringify({modificationCode: "0", playerName: username, planeModelModel: "Fighter"}),
        success: function (data) {
          location.reload();
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
               data: JSON.stringify({modificationCode: "1", playerName: username, planeModelModel: "Fighter"}),
               success: function (data) {
                 location.reload();
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
            data: JSON.stringify({modificationCode: "2", playerName: username, planeModelModel: "Fighter"}),
            success: function (data) {
              location.reload();
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
            data: JSON.stringify({modificationCode: "3", playerName: username, planeModelModel: "Fighter"}),
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
            data: JSON.stringify({modificationCode: "4", playerName: username, planeModelModel: "Fighter"}),
            success: function (data) {
              location.reload();
            },
            error: function (xhr, ajaxOptions, thrownError) {
              console.log('Could not log out')
            },
          });
        });


});