$(document).ready(function(){
     getConstructorBean();
     getFieldBean();
     getSetterBean();
});

function getConstructorBean(){
	
	$.ajax({
		  type: "GET",
		  url: _getConstructorBean,
		  contentType:"application/json",
		  async:false,
		  success: function(response){
			  $('#sBean1ForCon').val(response.stringBean1);
			  $('#sBean2ForCon').val(response.stringBean2);
			  $('#intBeanForCon').val(response.integerBean);
		  }
	});
}

function getFieldBean(){
	
	$.ajax({
		  type: "GET",
		  url: _getFieldBean,
		  contentType:"application/json",
		  async:false,
		  success: function(response){
			  $('#sBean1ForField').val(response.stringBean1);
			  $('#sBean2ForField').val(response.stringBean2);
			  $('#intBeanForField').val(response.integerBean);
		  }
	});
}

function getSetterBean(){
	
	$.ajax({
		  type: "GET",
		  url: _getSetterBean,
		  contentType:"application/json",
		  async:false,
		  success: function(response){
			  $('#sBean1ForSetter').val(response.stringBean1);
			  $('#sBean2ForSetter').val(response.stringBean2);
			  $('#intBeanForSetter').val(response.integerBean);
		  }
	});
}

function setConstructorBean(){
	
	$.ajax({
		  type: "PUT",
		  url: _getConstructorBean + "?stringBean1=" + $('#sBean1ForCon').val() + "&stringBean2=" + $('#sBean2ForCon').val() + "&integerBean=" + $('#intBeanForCon').val(),
		  contentType:"application/json",
		  async:false,
		  success: function(response){
			  $('#con-div').notify("Successfully changed the bean values",{ position:"top", type: "success"});
			  $('#sBean1ForCon').val(response.stringBean1);
			  $('#sBean2ForCon').val(response.stringBean2);
			  $('#intBeanForCon').val(response.integerBean);
		  }
	});
}

function setFieldBean(){
	
	$.ajax({
		  type: "PUT",
		  url: _getFieldBean + "?stringBean1=" + $('#sBean1ForField').val() + "&stringBean2=" + $('#sBean2ForField').val() + "&integerBean=" + $('#intBeanForField').val(),
		  contentType:"application/json",
		  async:false,
		  success: function(response){
			  $('#field-div').notify("Successfully changed the bean values",{ position:"top", type: "success"});
			  $('#sBean1ForField').val(response.stringBean1);
			  $('#sBean2ForField').val(response.stringBean2);
			  $('#intBeanForField').val(response.integerBean);
		  }
	});
}

function setSetterBean(){
	
	$.ajax({
		  type: "PUT",
		  url: _getSetterBean + "?stringBean1=" + $('#sBean1ForSetter').val() + "&stringBean2=" + $('#sBean2ForSetter').val() + "&integerBean=" + $('#intBeanForSetter').val(),
		  contentType:"application/json",
		  async:false,
		  success: function(response){
			  $('#setter-div').notify("Successfully changed the bean values",{ position:"top", type: "success"});
			  $('#sBean1ForSetter').val(response.stringBean1);
			  $('#sBean2ForSetter').val(response.stringBean2);
			  $('#intBeanForSetter').val(response.integerBean);
		  }
	});
}