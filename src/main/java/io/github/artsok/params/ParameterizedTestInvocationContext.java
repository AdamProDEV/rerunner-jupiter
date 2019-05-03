/*
 * Copyright 2015-2019 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */

package io.github.artsok.params;

import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;

import java.util.Arrays;
import java.util.List;

/**
 * @since 5.0
 */
class ParameterizedTestInvocationContext implements TestTemplateInvocationContext {

    private final ParameterizedTestNameFormatter formatter;
    private final ParameterizedTestMethodContext methodContext;
    private final Object[] arguments;

    ParameterizedTestInvocationContext(ParameterizedTestNameFormatter formatter,
                                       ParameterizedTestMethodContext methodContext, Object[] arguments) {
        this.formatter = formatter;
        this.methodContext = methodContext;
        this.arguments = arguments;
    }

    @Override
    public String getDisplayName(int invocationIndex) {
        return this.formatter.format(invocationIndex, this.arguments);
    }

    @Override
    public List<Extension> getAdditionalExtensions() {
        return Arrays.asList(new ParameterizedTestParameterResolver(this.methodContext, this.arguments)
        );
        //Надо сюда инджектить дополнительную логику обработки ошибок. Чтобы обработчик был внутри данного темплайте, а не снанужи
    }

}

