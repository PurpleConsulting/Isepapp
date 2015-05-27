$(document).ready(function(){

  var slick = function(box, timer){
    box.children().slideToggle(timer);
    box.toggleClass("hidden-box");
  }

  var slickStatus = function(box){
    return box.children().first().css("display");
  }

  slick($(".alert-sub-skill-box"), 0);


  $("span.dropdown").click(function(){
      var box = $(this).parent().next();
      slick(box, "slow");
      $(this).toggleClass("fa-chevron-circle-down");
      $(this).toggleClass("fa-chevron-circle-up");
  });

  $("span.master-dropdown").click(function(){
      var btn = $(this)
      $(".alert-sub-skill-box").each(function(){
        var arrow = $(this).prev().children(".fa");
        if(btn.hasClass("fa-angle-double-down") && slickStatus($(this)) == "none"){
          slick($(this), "slow");
          arrow.toggleClass("fa-chevron-circle-down");
          arrow.toggleClass("fa-chevron-circle-up");
        } else if(btn.hasClass("fa-angle-double-up") && slickStatus($(this)) == "block") {
          slick($(this), "slow");
          arrow.toggleClass("fa-chevron-circle-down");
          arrow.toggleClass("fa-chevron-circle-up");
        }
      })

      $(this).toggleClass("fa-angle-double-down");
      $(this).toggleClass("fa-angle-double-up");
  });
});
