jQuery('document').ready(function ($){
    $('.menu-toggle').click(function () {
        $('#primary-menu').slideToggle()
    })


    // Initialize gototop for carousel
    if ( $('#toTop').length > 0 ) {
    // Hide the toTop button when the page loads.
    $("#toTop").css("display", "none");
    
      // This function runs every time the user scrolls the page.
      $(window).scroll(function(){

        // Check weather the user has scrolled down (if "scrollTop()"" is more than 0)
        if($(window).scrollTop() > 0){

          // If it's more than or equal to 0, show the toTop button.
          $("#toTop").fadeIn("slow");
        }
        else {
          // If it's less than 0 (at the top), hide the toTop button.
          $("#toTop").fadeOut("slow");

        }
      });

      // When the user clicks the toTop button, we want the page to scroll to the top.
      jQuery("#toTop").click(function($){

        // Disable the default behaviour when a user clicks an empty anchor link.
        // (The page jumps to the top instead of // animating)
        event.preventDefault();

        // Animate the scrolling motion.
        jQuery("html, body").animate({
          scrollTop:0
        },"slow");

      });
    }


})