package com.bymarcin.openglasses.lua.luafunction;

import ben_mkiv.rendertoolkit.common.widgets.WidgetModifierConditionType;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;

import com.bymarcin.openglasses.lua.LuaFunction;
import ben_mkiv.rendertoolkit.common.widgets.Widget;

public class SetCondition extends LuaFunction{

	@Override
	@Callback(direct = true)
	public Object[] call(Context context, Arguments arguments) {
		super.call(context, arguments);
		Widget widget = getWidget();
		if(widget != null){
			
			int modifierIndex = (int) arguments.checkInteger(0) - 1;
			boolean state = arguments.checkBoolean(2);
			short conditionIndex = WidgetModifierConditionType.getIndex(arguments.checkString(1));
			
			widget.WidgetModifierList.setCondition(modifierIndex, conditionIndex, state);

			updateWidget();
			return null;
		}
		throw new RuntimeException("Component does not exists!");
	}

	@Override
	public String getName() {
		return "setCondition";
	}

}
