import PropTypes from 'prop-types';
import { requireNativeComponent, ViewPropTypes } from 'react-native';
import {NativeModules} from 'react-native';

var customViewProps1 = {
    name : 'CustomViewKotlin',
    propTypes: {
        custom_view_kotlin: PropTypes.shape({
            text: PropTypes.string,
            resId: PropTypes.number
        }),
        ViewPropTypes
    }
}
module.exports = requireNativeComponent('CustomViewKotlin', customViewProps1);
