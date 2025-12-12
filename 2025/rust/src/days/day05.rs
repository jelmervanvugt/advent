use std::ops::RangeInclusive;

pub fn pt1(ranges: Vec<RangeInclusive<i64>>, ids: Vec<i64>) -> i64 {
    let mut fresh_amount = 0;
    for id in ids {
        if ranges.iter().any(|range| range.contains(&id)) {
            fresh_amount += 1;
        }
    }
    fresh_amount
}

pub fn pt2(ranges: &mut Vec<RangeInclusive<i64>>) -> i64 {
    ranges.sort_by(|a, b| a.start().cmp(b.start()));

    let mut optimized = true;
    while optimized {
        let mut tmp = ranges.clone();

        for i in 0..ranges.len() - 1 {
            let first = &ranges[i];
            let second = &ranges[i + 1];

            if first.start() == second.start() && first.end() == second.end() {
                // Precies hetzelfde
                tmp.remove(i);

                ranges.clear();
                ranges.append(&mut tmp);
                break;
            } else if first.start() <= second.start() && first.end() >= second.end() {
                // First bevat second
                tmp.remove(i + 1);

                ranges.clear();
                ranges.append(&mut tmp);
                break;
            } else if first.end() >= second.start() && first.end() < second.end() {
                // Second overlapped of sluit exact aan op first
                tmp.remove(i + 1);
                tmp[i] = merge_ranges(first, second);

                ranges.clear();
                ranges.append(&mut tmp);
                break;
            } else if i == ranges.len() - 2 {
                // Alleen als hele Vector is bekeken
                optimized = false;
            }
        }
    }

    let mut total = 0;
    for range in ranges {
        total += range.end() - range.start() + 1;
    }

    total
}

fn merge_ranges(r1: &RangeInclusive<i64>, r2: &RangeInclusive<i64>) -> RangeInclusive<i64> {
    let start = *r1.start().min(r2.start());
    let end = *r1.end().max(r2.end());
    start..=end
}

#[cfg(test)]
mod tests {
    use crate::utils::load_input_file;
    use super::*;

    #[test]
    fn test_pt1_example() {
        let (ranges, ids) = parse_input("day05/example_input");
        assert_eq!(pt1(ranges, ids), 3);
    }

    #[test]
    fn test_pt1() {
        let (ranges, ids) = parse_input("day05/input");
        assert_eq!(pt1(ranges, ids), 643);
    }

    #[test]
    fn test_pt2_example() {
        let (mut ranges, ids) = parse_input("day05/example_input");
        assert_eq!(pt2(&mut ranges), 14);
    }

    #[test]
    fn test_pt2() {
        let (mut ranges, ids) = parse_input("day05/input");
        assert_eq!(pt2(&mut ranges), 342018167474526);
    }

    fn parse_input(filename: &str) -> (Vec<RangeInclusive<i64>>, Vec<i64>) {
        let mut ranges = Vec::new();
        let mut ids = Vec::new();

        load_input_file(filename).lines().for_each(|l| {
            if l.contains('-') {
                let range: Vec<i64> = l
                    .split('-')
                    .map(|r| r.to_string().parse::<i64>().unwrap())
                    .collect();
                ranges.push(range[0]..=range[1]);
            } else if !l.is_empty() {
                ids.push(l.parse::<i64>().unwrap());
            }
        });
        (ranges, ids)
    }
}
