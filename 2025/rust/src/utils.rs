use std::fs;

pub fn load_input_file(input: &str) -> String {
    let file_path = format!("resources/{}", input);
    fs::read_to_string(file_path).expect(format!("File {} does not exist.", input).as_str())
}