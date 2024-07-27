package org.apeiron.kernel.commons.annotations;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.apeiron.kernel.IntegrationTest;
import org.apeiron.kernel.service.actionable.IAction;
import org.apeiron.kernel.service.dto.ActionDto;
import org.apeiron.kernel.service.dto.ArgumentDto;
import org.apeiron.kernel.service.dto.ContextDto;
import org.apeiron.kernel.service.dto.PropertyMapDto;
import org.apeiron.kernel.service.dto.RuleDto;
import org.apeiron.kernel.service.dto.TransicionDto;
import org.apeiron.kernel.service.util.ArgumentUtils;
import org.apeiron.kernel.service.validator.IRule;
import org.apeiron.kernel.utils.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@IntegrationTest
class AnnotationProcessorTest {

    @Autowired
    private List<IRule> reglas;

    @Autowired
    private List<IAction> acciones;

    private static final String ARGUMENT_RULE_NAME = "argumentsRule";
    private static final String ARGUMENT_ACTION_NAME = "argumentsAction";
    private static final String DATE_TIME_ARGUMENT = "dateTimeArgument";
    private static final String DATE_ARGUMENT = "dateArgument";
    private static final String INTEGER_ARGUMENT = "integerArgument";
    private static final String FLOAT_ARGUMENT = "floatArgument";
    private static final String STRING_ARGUMENT = "stringArgument";
    private static final String PROPERTY_MAP_ARGUMENT = "propertyMapArgument";
    private static final String NOT_EXISTING_PROPERTY_NAME = "propertyNotExisting";
    private RuleDto argumentRule = null;
    private ActionDto argumentAction = null;
    private IRule argumentRuleInstance = null;
    private IAction argumentActionInstance = null;
    private ArgumentDto dateTimeArgument = null;
    private ArgumentDto dateArgument = null;
    private ArgumentDto integerArgument = null;
    private ArgumentDto floatArgument = null;
    private ArgumentDto stringArgument = null;
    private ArgumentDto propertyMapArgument = null;
    private ContextDto context = null;

    @BeforeEach
    public void init() {
        this.dateTimeArgument = createArgument(ArgumentType.DATE_TIME, DATE_TIME_ARGUMENT, "2023-12-02T01:35:53.961Z");
        this.dateArgument = createArgument(ArgumentType.DATE, DATE_ARGUMENT, "2023-12-02");
        this.integerArgument = createArgument(ArgumentType.INTEGER, INTEGER_ARGUMENT, "100");
        this.floatArgument = createArgument(ArgumentType.FLOAT, FLOAT_ARGUMENT, "99.99");
        this.stringArgument = createArgument(ArgumentType.STRING, STRING_ARGUMENT, "apeiron");
        this.propertyMapArgument = createArgument(ArgumentType.PROPERTY_MAP, PROPERTY_MAP_ARGUMENT, mockPropertyMap());
        this.context = mockContext();

        this.argumentRuleInstance =
            reglas.stream().filter(regla -> getClave(regla).equals(ARGUMENT_RULE_NAME)).reduce((first, second) -> first).orElseThrow();
        this.argumentActionInstance =
            acciones
                .stream()
                .filter(accion -> getClave(accion).equals(ARGUMENT_ACTION_NAME))
                .reduce((first, second) -> first)
                .orElseThrow();

        this.argumentRule = AnnotationProcessor.resolveRule(this.argumentRuleInstance);
        this.argumentAction = AnnotationProcessor.resolveActions(this.argumentActionInstance);
    }

    private static String getClave(Object clazz) {
        return StringUtils.toCamelCase(clazz.getClass().getSimpleName());
    }

    @Test
    void testRuleAnnotationProcessor() {
        assertThat(this.argumentRule.getClave()).isNotEmpty().isEqualTo(ARGUMENT_RULE_NAME);
        assertThat(this.argumentRule.getArguments())
            .isNotEmpty()
            .anyMatch(argument -> isArgumentEquals(argument, dateTimeArgument))
            .anyMatch(argument -> isArgumentEquals(argument, dateArgument))
            .anyMatch(argument -> isArgumentEquals(argument, integerArgument))
            .anyMatch(argument -> isArgumentEquals(argument, floatArgument))
            .anyMatch(argument -> isArgumentEquals(argument, stringArgument))
            .anyMatch(argument -> isArgumentEquals(argument, propertyMapArgument));
    }

    @Test
    void testActionAnnotationProcessor() {
        assertThat(this.argumentAction.getClave()).isNotEmpty().isEqualTo(ARGUMENT_ACTION_NAME);
        assertThat(this.argumentAction.getArguments())
            .isNotEmpty()
            .anyMatch(argument -> isArgumentEquals(argument, dateTimeArgument))
            .anyMatch(argument -> isArgumentEquals(argument, dateArgument))
            .anyMatch(argument -> isArgumentEquals(argument, integerArgument))
            .anyMatch(argument -> isArgumentEquals(argument, floatArgument))
            .anyMatch(argument -> isArgumentEquals(argument, stringArgument))
            .anyMatch(argument -> isArgumentEquals(argument, propertyMapArgument));
    }

    @Test
    void testDateTimeArgumentMap() {
        Optional<Instant> dateTimeOptional = ArgumentUtils.asDateTime(
            this.context,
            this.argumentRuleInstance,
            this.dateTimeArgument.getName()
        );
        Optional<Instant> dateTimeEmptyOptional = ArgumentUtils.asDateTime(
            this.context,
            this.argumentRuleInstance,
            NOT_EXISTING_PROPERTY_NAME
        );
        assertThat(dateTimeOptional).isPresent();
        assertThat(dateTimeEmptyOptional).isNotPresent();
    }

    @Test
    void testDateArgumentMap() {
        Optional<LocalDate> dateOptional = ArgumentUtils.asDate(this.context, this.argumentRuleInstance, this.dateArgument.getName());
        Optional<LocalDate> dateEmptyOptional = ArgumentUtils.asDate(this.context, this.argumentRuleInstance, NOT_EXISTING_PROPERTY_NAME);

        assertThat(dateOptional).isPresent();
        assertThat(dateEmptyOptional).isNotPresent();
    }

    @Test
    void testIntegerArgumentMap() {
        Optional<Integer> integerOptional = ArgumentUtils.asInteger(
            this.context,
            this.argumentRuleInstance,
            this.integerArgument.getName()
        );
        Optional<Integer> integerEmptyOptional = ArgumentUtils.asInteger(
            this.context,
            this.argumentRuleInstance,
            NOT_EXISTING_PROPERTY_NAME
        );

        assertThat(integerOptional).isPresent();
        assertThat(integerEmptyOptional).isNotPresent();
    }

    @Test
    void testFloatArgumentMap() {
        Optional<Float> floatOptional = ArgumentUtils.asFloat(this.context, this.argumentRuleInstance, this.floatArgument.getName());
        Optional<Float> floatEmptyOptional = ArgumentUtils.asFloat(this.context, this.argumentRuleInstance, NOT_EXISTING_PROPERTY_NAME);

        assertThat(floatOptional).isPresent();
        assertThat(floatEmptyOptional).isNotPresent();
    }

    @Test
    void testStringArgumentMap() {
        Optional<String> stringOptional = ArgumentUtils.asString(this.context, this.argumentRuleInstance, this.dateTimeArgument.getName());
        Optional<String> stringEmptyOptional = ArgumentUtils.asString(this.context, this.argumentRuleInstance, NOT_EXISTING_PROPERTY_NAME);

        assertThat(stringOptional).isPresent();
        assertThat(stringEmptyOptional).isNotPresent();
    }

    @Test
    void testPropertyMapArgumentMap() {
        Optional<List<PropertyMapDto>> propertyMapOptional = ArgumentUtils.asPropertyMap(
            this.context,
            this.argumentRuleInstance,
            this.propertyMapArgument.getName()
        );
        Optional<List<PropertyMapDto>> propertyMapEmptyOptional = ArgumentUtils.asPropertyMap(
            this.context,
            this.argumentRuleInstance,
            NOT_EXISTING_PROPERTY_NAME
        );

        assertThat(propertyMapOptional).isPresent();
        assertThat(propertyMapOptional.get()).containsAll(mockPropertyMap());
        assertThat(propertyMapEmptyOptional.get()).isEmpty();
    }

    private static boolean isArgumentEquals(ArgumentDto left, ArgumentDto right) {
        if (!left.getName().equals(right.getName())) {
            return false;
        }

        if (!left.getType().equals(right.getType())) {
            return false;
        }

        return true;
    }

    private static final ArgumentDto createArgument(ArgumentType type, String name, Object value) {
        return ArgumentDto.builder().type(type).name(name).value(value).build();
    }

    private ContextDto mockContext() {
        ArgumentDto[] argumentos = new ArgumentDto[] {
            this.dateArgument,
            this.dateTimeArgument,
            this.integerArgument,
            this.floatArgument,
            this.stringArgument,
            this.propertyMapArgument,
        };
        List<RuleDto> reglas = Arrays.asList(RuleDto.builder().clave(ARGUMENT_RULE_NAME).arguments(argumentos).build());
        List<ActionDto> acciones = Arrays.asList(ActionDto.builder().clave(ARGUMENT_ACTION_NAME).arguments(argumentos).build());
        TransicionDto transicion = new TransicionDto();
        transicion.setReglas(reglas);
        transicion.setAcciones(acciones);
        return ContextDto.builder().transicion(transicion).build();
    }

    private static List<PropertyMapDto> mockPropertyMap() {
        return Arrays.asList(
            PropertyMapDto
                .builder()
                .type(PropertyMapType.FROM_OBJECT)
                .value("propertyMapFromObjectValue")
                .from("solicitud.solicitante.cvu")
                .to("formalizacion.solicitante.cvu")
                .build(),
            PropertyMapDto
                .builder()
                .type(PropertyMapType.FROM_INLINE_VALUE)
                .value("inlineValue")
                .from("calificacion")
                .to("formalizacion.solicitante.cvu")
                .build()
        );
    }
}
