import OnnxRuntime from 'onnxruntime-react-native';

const InferenceSession = OnnxRuntime.InferenceSession;

export async function post(message){
    const sess = await InferenceSession.create("./monokamo.onnx")
    const feeds = {text: new OnnxRuntime.Tensor([message] , [1,1])};
    return await sess.run(feeds, [sess.outputNames[0]])
}
