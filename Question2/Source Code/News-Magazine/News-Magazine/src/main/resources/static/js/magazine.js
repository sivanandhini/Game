$(document).ready(function() {
	
	fetchHeadLines('in');
});


function fetchHeadLines(countryCode){
	var req = new Request(_getHeadlineNewsUrl.replace('{country}', countryCode));
	fetch(req)
	    .then(function(response) {
	    	response.json().then(function(source) { 
	    		var headlineHtml = "";
	    		$('#headline-carousel-inner').html('');
	    		$.each(source.articles, function(i, item) {
	    			var active = (i == 0) ? 'active' : ''; 
	    			headlineHtml = headlineHtml + '<div class="item '+active+'" style="height:550px"><a href="'+item.url+'" target="_blank"><img src="'+item.urlToImage+'"  style="width:100%;"/></a>' + 
	    			               '<div class="carousel-caption">'+
	    	                       '<h3>'+item.title+'</h3>' + 
	    	                       '<p>'+item.description+'</p>' + 
	    	                       '</div></div>';
	    		});
	    		$('#headline-carousel-inner').html(headlineHtml);
	        })
	    })
}

function changeCountry(countryCode){
	$(".navbar-n a").removeClass("dropdown-active");
	$(".navbar-n div").removeClass("dropdown-active");
	$('#headlines').addClass("dropdown-active");
	fetchHeadLines(countryCode);
	showHeadlines();
}

function showHeadlines(){
	$(".navbar-n a").removeClass("dropdown-active");
	$(".navbar-n div").removeClass("dropdown-active");
	$('#headlines').addClass("dropdown-active");
	$('.headlines-div').show();
	$('#channel-news-div').hide();
	$('#search-news-div').hide();
	$('#topics-news-div').hide();
	$('#searchBox').val('');
}

function searchNews(obj){
	$(".navbar-n a").removeClass("dropdown-active");
	$(".navbar-n div").removeClass("dropdown-active");
	$(obj).addClass("dropdown-active");
	$('.headlines-div').hide();
	$('#channel-news-div').hide();
	$('#search-news-div').show();
	$('#topics-news-div').hide();
	$('#searchBox').val('');
}

$(document).ready(function() {
	$('#searchBox').keypress(function(event){
	    var keycode = (event.keyCode ? event.keyCode : event.which);
	    if(keycode == '13'){
	    	var searchText = $('#searchBox').val();
	    	var req = new Request(_searchNewsSourceUrl.replace('{searchText}', searchText));
	    	fetch(req)
	    	    .then(function(response) {
	    	    	response.json().then(function(source) { 
	    	    		var headlineHtml = "";
	    	    		$('#search-carousel-inner').html('');
	    	    		$.each(source.articles, function(i, item) {
	    	    			headlineHtml = headlineHtml + '<div class="item active" style="height:300px;width: 507px;margin-left: 350px;margin-top: 52px;margin-bottom: 65px;"><a href="'+item.url+'" target="_blank"><img src="'+item.urlToImage+'" />' + 
	    	    			               '<div class="carousel-caption">'+
	    	    	                       '<h3>'+item.title+'</h3>' + 
	    	    	                       '<p>'+item.description+'</p>' + 
	    	    	                       '</div></a></div>';
	    	    		});
	    	    		$('#search-carousel-inner').html(headlineHtml);
	    	        })
	    	    })
	    }
	});
})

function changeTopics(topic){
	
	$(".navbar-n a").removeClass("dropdown-active");
	$(".navbar-n div").removeClass("dropdown-active");
	$('.topc').addClass("dropdown-active");
	
	$('.headlines-div').hide();
	$('#channel-news-div').hide();
	$('#search-news-div').hide();
	$('#topics-news-div').show();
	
	var req = new Request(_getTopicsUrl.replace('{topic}', topic));
	fetch(req)
	    .then(function(response) {
	    	response.json().then(function(source) { 
	    		var headlineHtml = "";
	    		$('#topics-carousel-inner').html('');
	    		$.each(source.articles, function(i, item) {
	    			var active = (i == 0) ? 'active' : ''; 
	    			headlineHtml = headlineHtml + '<div class="item active" style="height:300px;width: 507px;margin-left: 350px;margin-top: 52px;margin-bottom: 65px;"><a href="'+item.url+'" target="_blank"><img src="'+item.urlToImage+'"  style="width:100%;"/></a>' + 
	    			               '<div class="carousel-caption">'+
	    	                       '<h3>'+item.title+'</h3>' + 
	    	                       '<p>'+item.description+'</p>' + 
	    	                       '</div></div>';
	    		});
	    		$('#topics-carousel-inner').html(headlineHtml);
	        })
	    })
	
}

function changeNewsChannel(channel){
	
	$(".navbar-n a").removeClass("dropdown-active");
	$(".navbar-n div").removeClass("dropdown-active");
	$('.chnl').addClass("dropdown-active");
	
	$('.headlines-div').hide();
	$('#channel-news-div').show();
	$('#search-news-div').hide();
	$('#topics-news-div').hide();
	
	var req = new Request(_getTopicsUrl.replace('{topic}', channel));
	fetch(req)
	    .then(function(response) {
	    	response.json().then(function(source) { 
	    		var headlineHtml = "";
	    		$('#channel-carousel-inner').html('');
	    		$.each(source.articles, function(i, item) {
	    			var active = (i == 0) ? 'active' : ''; 
	    			headlineHtml = headlineHtml + '<div class="item active" style="height:300px;width: 507px;margin-left: 350px;margin-top: 52px;margin-bottom: 65px;"><a href="'+item.url+'" target="_blank"><img src="'+item.urlToImage+'"  style="width:100%;"/></a>' + 
	    			               '<div class="carousel-caption">'+
	    	                       '<h3>'+item.title+'</h3>' + 
	    	                       '<p>'+item.description+'</p>' + 
	    	                       '</div></div>';
	    		});
	    		$('#channel-carousel-inner').html(headlineHtml);
	        })
	    })
	
}