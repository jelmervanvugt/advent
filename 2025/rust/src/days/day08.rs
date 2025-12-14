use std::collections::HashMap;

struct Junction {
    x: i64,
    y: i64,
    z: i64,
    circuit: Option<i64>,
}

impl Junction {
    fn new(x: i64, y: i64, z: i64) -> Self {
        Self {
            x,
            y,
            z,
            circuit: None,
        }
    }
}

fn solve(junctions: &mut Vec<Junction>, max_connections: i64) -> i64 {
    let mut pairs: Vec<(usize, usize, f64)> = generate_pairs(junctions);
    pairs.sort_by(|a, b| a.2.partial_cmp(&b.2).unwrap());

    let mut connections_made = 0;
    let mut circuit_no = 1;

    for pair in pairs {
        if connections_made == max_connections {
            break;
        }

        let first_idx = pair.0;
        let second_idx = pair.1;

        let first_circuit = junctions[first_idx].circuit;
        let second_circuit = junctions[second_idx].circuit;

        connections_made += 1;

        if first_circuit.is_none() && second_circuit.is_some() {
            junctions[first_idx].circuit = second_circuit;
        } else if first_circuit.is_some() && second_circuit.is_none() {
            junctions[second_idx].circuit = first_circuit;
        } else if first_circuit.is_none() && second_circuit.is_none() {
            junctions[first_idx].circuit = Some(circuit_no);
            junctions[second_idx].circuit = Some(circuit_no);
            circuit_no += 1;
        } else if first_circuit != second_circuit {
            let old_circuit = second_circuit.unwrap();
            let new_circuit = first_circuit.unwrap();
            for junction in junctions.iter_mut() {
                if junction.circuit == Some(old_circuit) {
                    junction.circuit = Some(new_circuit);
                }
            }
        }
    }

    calculate_result(junctions)
}

fn calculate_result(junctions: &Vec<Junction>) -> i64 {
    let mut counts: HashMap<i64, i64> = HashMap::new();
    for junction in junctions {
        if let Some(circuit) = junction.circuit {
            *counts.entry(circuit).or_insert(0) += 1;
        }
    }
    let mut values: Vec<i64> = counts.values().cloned().collect();
    values.sort_by(|a, b| b.cmp(a));

    values.iter().take(3).product()
}

fn euclidian(p1: &Junction, p2: &Junction) -> f64 {
    (((p1.x - p2.x).pow(2) + (p1.y - p2.y).pow(2) + (p1.z - p2.z).pow(2)) as f64).sqrt()
}

fn generate_pairs(junctions: &mut Vec<Junction>) -> Vec<(usize, usize, f64)> {
    let mut pairs: Vec<(usize, usize, f64)> = Vec::new();
    for i in 0..junctions.len() {
        for j in (i + 1)..junctions.len() {
            pairs.push((i, j, euclidian(&junctions[i], &junctions[j])))
        }
    }
    pairs
}

#[cfg(test)]
mod tests {
    use super::*;
    use crate::utils::load_input_file;

    #[test]
    fn test_pt1_example() {
        let mut junctions = parse_junctions("day08/example_input");
        assert_eq!(solve(&mut junctions, 10), 40);
    }

    #[test]
    fn test_pt1() {
        let mut junctions = parse_junctions("day08/input");
        assert_eq!(solve(&mut junctions, 1000), 135169);
    }

    fn parse_junctions(input: &str) -> Vec<Junction> {
        let mut junctions: Vec<Junction> = Vec::new();
        load_input_file(input).lines().for_each(|line| {
            let values: Vec<i64> = line.split(',').map(|n| n.parse::<i64>().unwrap()).collect();
            junctions.push(Junction::new(values[0], values[1], values[2]));
        });

        junctions
    }
}
