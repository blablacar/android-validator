# Android validator

[ ![Download](https://api.bintray.com/packages/blablacar/maven/android-validator/images/download.svg) ](https://bintray.com/blablacar/maven/android-validator/_latestVersion)

Validate form with predefined constraints like not blank, email, file ...

## Main features

- Validate EditText, Spinner, String with: Blank, Date, Email, Equals, Length, Name, NotBlank, Not Equals, Url constraints.
- Validate File with: File and Image constraints.
- Validate RadioGroup & CompoundButton with: IsCheckedConstraint.
- **Tested**: > 40 tests.

## Download
The library is available on **JCenter**:
```gradle
compile 'com.comuto:validator:0.1'
```

## Usage

Validate an User address:

```java
    public class AddressFormValidator extends Validator {
        private static final String PROPERTY_ADDRESS = "address";
        private static final String PROPERTY_CITY = "city";
        private static final String PROPERTY_STATE = "state";
        private static final String PROPERTY_ZIP_CODE = "zipCode";
        private static final String PROPERTY_COUNTRY = "country";

        private final TextInputEditText address;
        private final TextInputEditText city;
        private final TextInputEditText zipCode;
        private final TextView stateError;
        private final TextView countryError;

        public AddressFormValidator(TextInputEditText address, TextInputEditText city, AppCompatSpinner state,
            TextInputEditText zipCode, AppCompatSpinner country, TextView stateError, TextView countryError) {
            this.address = address;
            this.city = city;
            this.zipCode = zipCode;
            this.stateError = stateError;
            this.countryError = countryError;

            add(new NotBlankConstraint(address, PROPERTY_ADDRESS));
            add(new NotBlankConstraint(city, PROPERTY_CITY));
            add(new NotEqualsConstraint(state, "State", PROPERTY_STATE));
            add(new NotBlankConstraint(zipCode, PROPERTY_ZIP_CODE));
            add(new NotEqualsConstraint(country, "Country", PROPERTY_COUNTRY));
        }
    }
```

```java
    validator = new AddressFormValidator(address, city, state, zipCode, country, stateError, countryError);
    Set<Violation> violations = validator.validate();

    if(!violations.isEmpty()) {
        for (Violation violation : violations) {
            if (PROPERTY_ADDRESS.equals(violation.getPropertyName())) {
                setTextInputLayoutError(address, violation.getMessage());
            } else if (PROPERTY_CITY.equals(violation.getPropertyName())) {
                ((TextInputLayout) city.getParent()).setError(violation.getMessage());
                setTextInputLayoutError(city, violation.getMessage());
            } else if (PROPERTY_STATE.equals(violation.getPropertyName())) {
                stateError.setVisibility(View.VISIBLE);
                stateError.setText(violation.getMessage());
            } else if (PROPERTY_ZIP_CODE.equals(violation.getPropertyName())) {
                setTextInputLayoutError(zipCode, violation.getMessage());
            } else if (PROPERTY_COUNTRY.equals(violation.getPropertyName())) {
                countryError.setVisibility(View.VISIBLE);
                countryError.setText(violation.getMessage());
            }
        }
    } else {
        Toast.makeText(this, "Is Valid", Toast.LENGTH_LONG).show();
    }
```

## Sample app

See the sample application available in `sample/src/main`

## License

    Copyright (C) 2015 BlaBlaCar (http://www.blablacar.com)

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.