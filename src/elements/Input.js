import styled from "styled-components";

const Input = (props) => {
    const {
        label,
        type,
        placeholder,
        value,
        _onChange,
        is_submit,
        _onSubmit,
        textarea,
        margin,
        width,
    } = props;
    return (
        <InputBox 
            style={{ width }}
            placeholder={placeholder}
        ></InputBox>
    )
}

Input.defaultProps = {
    label: false,
    type: "text",
    placeholder: "입력해주세용!",
    value: "",
    is_submit: false,
    is_upload: false,
    _onChange: () => {},
    _onSubmit: () => {},
    margin: false,
    width: false,
  };

const InputBox = styled.input`
    margin : 20px 0;
    outline : none;
    border: 2px solid rgba(108,117,125,0.3);
    border-radius: 5px;
    height : 45px;
    transition : .5s;
    ${(props) => (props.width ? `width: ${props.width};` : `width: 100%;`)}    padding : 10px;
    &:focus {
        border: 2px solid rgba(108,117,125,0.8);
    }
`;

export default Input;