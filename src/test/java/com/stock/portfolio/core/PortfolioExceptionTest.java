package com.stock.portfolio.core;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PortfolioExceptionTest {

    private enum TestErrors implements ErrorMessage {
        TEST_ERROR_NO_ARGUMENTS("This is an error without arguments"),
        TEST_ERROR_ONE_ARGUMENT("This is an error without argument {}"),
        TEST_ERROR_TWO_ARGUMENTS("This is an error with first argument {} and second {}");

        private String message;

        TestErrors(String message) {
            this.message = message;
        }


        @Override
        public String getMessage() {
            return message;
        }
    }

    @Test
    public void whenNoArgumentProvided_shouldReturnMessage() {
        PortfolioException portfolioException = new PortfolioException(TestErrors.TEST_ERROR_NO_ARGUMENTS);

        Assertions.assertThat(portfolioException.getMessage()).isEqualTo("This is an error without arguments");
    }

    @Test
    public void whenOneArgumentProvided_shouldReturnMessageWithThisArgument() {
        PortfolioException portfolioException = new PortfolioException(TestErrors.TEST_ERROR_ONE_ARGUMENT, 1);

        Assertions.assertThat(portfolioException.getMessage()).isEqualTo("This is an error without argument 1");
    }

    @Test
    public void whenTwoArgumentsProvided_shouldReturnMessageWithTwoArguments() {
        PortfolioException portfolioException = new PortfolioException(TestErrors.TEST_ERROR_TWO_ARGUMENTS, 1, "Arg");

        Assertions.assertThat(portfolioException.getMessage()).isEqualTo("This is an error with first argument 1 and second Arg");
    }
}
