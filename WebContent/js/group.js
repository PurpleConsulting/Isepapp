/**
 * 
 */

$('button').tooltip({placement: 'bottom',trigger: 'manual'}).tooltip('show');
$('button').on('click',function(){$(this).tooltip('destroy');});