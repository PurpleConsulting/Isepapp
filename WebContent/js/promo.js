/**
 * 
 */

$('body').scrollspy({
    target: '.bs-docs-sidebar',
    offset: 62
});
$("#sidebar").affix({
    offset: {
      top: 162
    }
});

$("a.list-group-item").on("click", function(e){
	e.preventDefault();
})