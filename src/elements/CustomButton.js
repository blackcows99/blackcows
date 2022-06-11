import styled from "styled-components";

const CustomButton = (props) => {
    const {
        text,
        _onClick,
        width,
        is_block,
    } = props;

    const styles = {
        width,
        is_block,
        };

    return (
        <Button onClick={_onClick} {...styles} >{text}</Button>
    )
}
CustomButton.defaultProps = {
    _onChange: () => {},
    _onSubmit: () => {},
    _onClick: () => {},
    width: false,
    text:"",
    is_block:false,
  };

const Button = styled.span`
    background-color: #6c757d;
    color: white;
    padding : 12px;
    border-radius: .25rem;
    cursor : pointer;
    margin-left : 5px;
    transition: .3s;
    text-align: center;
    ${(props) => (props.width ? `width: ${props.width};` : "")};
    ${(props) => (props.is_block ? `display: block;` : "")};
    &:hover {
        background-color: rgba(108,117,125,0.7);

    }
`;
export default CustomButton;