import PropTypes from 'prop-types';
import { requireNativeComponent, ViewPropTypes } from 'react-native';
var customViewProps = {
    name : 'CustomView',
    propTypes: {
        custom_view:PropTypes.shape({
           text2: PropTypes.string,
           resId: PropTypes.number
        }),
        ViewPropTypes
    }
}
module.exports = requireNativeComponent('CustomView', customViewProps);
