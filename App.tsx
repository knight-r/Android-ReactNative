/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React,{useEffect,useRef,useState} from 'react';
import {View,Text, Button, NativeModules,findNodeHandle, StyleSheet} from 'react-native';
import CustomViewKotlin from './CustomViewKotlin';
import CustomViewModule from './CustomModule';
// import CustomView from './CustomView';
const App=()=> {
  const customViewRef = useRef(null);
 
  const [fetchedText, setFetchedText] = useState<string | null>(null); // <--- State variable for fetched text
  
  // Create a string array and pass it to android
  const strings = ['Hello', 'World', 'React Native'];  
  CustomViewModule.receiveStringArray(strings);

  CustomViewModule.receiveString("Ayush Kushwaha");
  CustomViewModule.receiveInt(50);
  
  const fetchEditTextValue = () => {
   
    if (customViewRef && customViewRef.current) {
      const reactTag = findNodeHandle(customViewRef.current);
      CustomViewModule.getTextFromET(reactTag).then(text => {
        console.log("ReactNative",text);
        setFetchedText(text);
      }).catch(error => {
        console.error("Failed to get text: ", error);
      });

      CustomViewModule.sendString(reactTag).then(text => {
        console.log("ReactNativeString",text);
      }).catch(error => {
        console.error("Failed to get text: ", error);
      });

      CustomViewModule.sendInt(reactTag).then(integer => {
        console.log("ReactNativeInt",integer.toString());
      }).catch(error => {
        console.error("Failed to get text: ", error);
      });
    }
};
    return (
      <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        {/* <CustomView
          custom_view = {{
            text1:  "Hello Folks!",
            text2: "I am from android side",
            resId: require('./assets/example_image.png')
          }}
          style={{ width: '30%', height: '30%' }} 
         /> */}
         
       <CustomViewKotlin
          ref={customViewRef}
          custom_view_kotlin={{
            text: "I am from native android side",
            resId: require('./assets/example_image.png')
          }}
          style={{ width: '50%', height: '50%' }}
          
       />
     
     <Button 
        title="fetch text" 
        onPress={() => {
          fetchEditTextValue();
          // setTextVisible(!textVisible);

        }}
      />

      {/* {textVisible && <Text style={styles.text}>Toggled Text!</Text>} */}
      {fetchedText && (
        <Text style={{ marginTop: 20 }}>${fetchedText}</Text>
      )}
        
      </View>
    )
  
}
const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  text: {
    marginTop: 20,
    fontSize: 18,
  },
});

   
export default App;