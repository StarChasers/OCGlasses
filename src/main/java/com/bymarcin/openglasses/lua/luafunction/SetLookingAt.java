package com.bymarcin.openglasses.lua.luafunction;

import ben_mkiv.rendertoolkit.common.widgets.core.attribute.ILookable;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;

import com.bymarcin.openglasses.lua.LuaFunction;
import ben_mkiv.rendertoolkit.common.widgets.Widget;

public class SetLookingAt extends LuaFunction{

	@Override
	@Callback(direct = true)
	public Object[] call(Context context, Arguments arguments) {
		super.call(context, arguments);
		Widget widget = getWidget();
		if(widget instanceof ILookable){
			if(arguments.isBoolean(0)){
				((ILookable) widget).setLookingAtEnable(arguments.checkBoolean(0));
			}else{
				((ILookable) widget).setLookingAt(arguments.checkInteger(0),arguments.checkInteger(1), arguments.checkInteger(2));
			}
			updateWidget();
			return null;
		}
		throw new RuntimeException("Component does not exists!");
	}

	@Override
	public String getName() {
		return "setLookingAt";
	}

}
