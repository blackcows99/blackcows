const Form = ({ mode, onClick, data }) => {
    const onSubmit = () => {
      // ref
      // const object = {}
      onClick(object);
    };
  
    return (
      <form>
        <input type="text" value={mode === "add" ? "" : data.value} />
        <button type="button" onClick={onClick}>
          {mode === "add" ? "추가하기" : "수정하기"}
        </button>
      </form>
    );
  };
  
  const AddPage = () => {
    const handleAdd = (object) => {
      // dispatch ~~  db에 저장할 수 있는 로직
    };
    return (
      <div>
        <Form mode="add" onClick={handleAdd} />
      </div>
    );
  };
  
  const EditPage = () => {
    // const id
    useEffect(() => {
      // load(id)
    }, []);
  
    const data = { value: "abc" };
  
    const handleEdit = () => {};
    return (
      <div>
        <Form mode="edit" onClick={handleEdit} data={data} />
      </div>
    );
  };
  