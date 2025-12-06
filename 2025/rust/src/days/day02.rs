pub fn solve(input: String, part_one: bool) -> i64 {
    let ids: Vec<i64> = input
        .split(|c| c == '-' || c == ',')
        .map(|c| c.parse::<i64>().unwrap())
        .collect();

    let mut invalid = 0;
    for id_pair in ids.chunks(2) {
        let range = id_pair[0]..=id_pair[1];
        for number in range {
            let is_invalid = if part_one {
                check_invalid(number.to_string())
            } else {
                check_invalid_2(number.to_string())
            };

            if is_invalid {
                invalid += number;
            }
        }
    }
    invalid
}

pub fn check_invalid(id: String) -> bool {
    if id.len() % 2 != 0 {
        return false;
    }
    let (first, second) = id.split_at(id.len() / 2);
    first == second
}

pub fn check_invalid_2(id: String) -> bool {
    let mut chunk_size = 1;
    while chunk_size <= id.chars().count() / 2 {
        let segments = id
            .chars()
            .collect::<Vec<char>>()
            .chunks(chunk_size)
            .map(|chunk| chunk.iter().collect::<String>())
            .collect::<Vec<String>>();

        if segments.windows(2).all(|w| w[0] == w[1]) {
            return true;
        }
        chunk_size += 1;
    }
    false
}

#[cfg(test)]
mod tests {
    use super::*;
    use crate::utils;

    #[test]
    fn test_pt1_example() {
        let input = utils::load_input_file("day02/example_input");
        assert_eq!(solve(input, true), 1227775554);
    }

    #[test]
    fn test_pt1() {
        let input = utils::load_input_file("day02/input");
        assert_eq!(solve(input, true), 28844599675);
    }

    #[test]
    fn test_pt2_example() {
        let input = utils::load_input_file("day02/example_input");
        assert_eq!(solve(input, false), 4174379265);
    }

    #[test]
    fn test_pt2() {
        let input = utils::load_input_file("day02/input");
        assert_eq!(solve(input, false), 48778605167);
    }
}
