package com.bymarcin.openglasses.lua.luafunction;

import ben_mkiv.rendertoolkit.common.widgets.core.attribute.ICustomShape;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;

import com.bymarcin.openglasses.lua.LuaFunction;
import ben_mkiv.rendertoolkit.common.widgets.Widget;

public class AddVertex extends LuaFunction{

    @Override
    @Callback(direct = true)
    public Object[] call(Context context, Arguments arguments) {
        super.call(context, arguments);
        Widget widget = getWidget();
        if(widget instanceof ICustomShape){
           int index = ((ICustomShape) widget).addVertex((float) arguments.checkDouble(0), (float) arguments.checkDouble(1), (float) arguments.checkDouble(2));
            updateWidget();
           return new Object[]{ index };
        }
        throw new RuntimeException("Component does not exists!");
    }

    @Override
    public String getName() {
        return "addVertex";
    }

}
