/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avenuecode.rafaelalbergaria.service.anotacoes;

import static java.lang.annotation.ElementType.FIELD;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.PARAMETER;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

@Qualifier @Retention(RUNTIME) @Target({TYPE, METHOD, FIELD, PARAMETER})
public @interface ServicoRN {
}