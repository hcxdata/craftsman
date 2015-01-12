Ext.ns("ArrayUtil");
ArrayUtil.contains=function (array,value,fun)
{
	if(Ext.isEmpty(fun))
		 return array.indexOf(value) == -1;
	var contains = false;
	for(var i = 0; i < array.length;i++)
	{
		if(fun.call(array,array[i],value))
		{
			contains = true;
			break;
		}
	}
	return contains;
}
ArrayUtil.get=function (array,value,fun)
{
	if(Ext.isEmpty(fun))
		 return array.indexOf(value) == -1;
	var obj = {};
	for(var i = 0; i < array.length;i++)
	{
		if(fun.call(array,array[i],value))
		{
			obj = array[i];
			break;
		}
	}
	return obj;
}