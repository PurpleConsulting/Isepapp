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