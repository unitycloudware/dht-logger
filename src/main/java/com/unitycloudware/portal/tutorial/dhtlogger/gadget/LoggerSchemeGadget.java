/* Copyright 2018 Unity{Cloud}Ware - UCW Industries Ltd. All rights reserved.
 */

package com.unitycloudware.portal.tutorial.dhtlogger.gadget;

import java.util.HashMap;
import java.util.Map;

import org.nsys.portal.gadget.AbstractGadget;

/**
 * DHT Logger Scheme Gadget
 *
 * @author Tomas Hrdlicka <tomas@hrdlicka.co.uk>
 * @see <a href="http://unitycloudware.com">Unity{Cloud}Ware</a>
 */
public class LoggerSchemeGadget extends AbstractGadget {

    public static final String TEMPLATE = "/templates/gadget/logger-scheme.vm";

    public LoggerSchemeGadget() {
        setTemplate(TEMPLATE);
    }

    @Override
    protected Map<String, Object> createVelocityParams(final Map<String, Object> context) {
        Map<String, Object> velocityParams = new HashMap<String, Object>();
        return velocityParams;
    }
}