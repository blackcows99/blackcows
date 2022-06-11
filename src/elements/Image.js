import styled from "styled-components"

const Image = (props) => {
    const { width, height, src } = props;
    const styles = { width, height, src };
    return (
        <ImageBox {...styles} />
    )
}

Image.defaultProps = {
    src: "https://user-images.githubusercontent.com/75834421/124501682-fb25fd00-ddfc-11eb-93ec-c0330dff399b.jpg",
    size: 40,
    half: false,
};

const ImageBox = styled.img`
    ${(props) => (props.width ? `width: ${props.width};` : `width: 100%;`)}
    ${(props) => (props.height ? `height: ${props.height};` : `height: 50vh;`)}
    background-size: cover;
    box-sizing : border-box;
    background-image: url("${(props) => props.src ? props.src : "https://user-images.githubusercontent.com/75834421/124501682-fb25fd00-ddfc-11eb-93ec-c0330dff399b.jpg"}");
    
`;

export default Image;