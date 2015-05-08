/**
 * 
 */

$('body').scrollspy({
    target: '.bs-docs-sidebar',
    offset: 0
});
$("#sidebar").affix({
    offset: {
      top: 210
    }
});

$("a.list-group-item").on("click", function(e){
	e.preventDefault();
})