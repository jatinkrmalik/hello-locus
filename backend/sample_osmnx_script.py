import osmnx as ox
import networkx as nx

ox.config(log_console=True)

# G = ox.graph_from_place('Bellandur, Bangalore, India', network_type='drive')
location_point = (12.9149, 77.6788)

# create network from point, inside bounding box of N, S, E, W each 750m from point
G = ox.graph_from_point(location_point, distance=2000,
                        distance_type='bbox', network_type='drive', simplify=False)
# G = ox.project_graph(G)
# fig, ax = ox.plot_graph(G, node_size=50, node_color='#66cc66')


nodes = [
    (12.9078, 77.6886),
    (12.9075, 77.6893),
    (12.9080, 77.6896),
    (12.9086, 77.6898),
    (12.9081, 77.6924),
    (12.9077, 77.6942),
    (12.9091, 77.6962),
    (12.9101, 77.6972),
    (12.9110, 77.698),
    (12.9123, 77.6983),
    (12.9154, 77.6986),
    (12.9156, 77.698),
    (12.9158, 77.6953),
    (12.9157, 77.6936),
    (12.9155, 77.6915),
    (12.9155, 77.6908),
    (12.9155, 77.6892),
    (12.9170, 77.6893),
    (12.9175, 77.6898),
    (12.9180, 77.6902),
    (12.9180, 77.6907),
    (12.9178, 77.6918),
    (12.9179, 77.6913),
    (12.9177, 77.6908),
    (12.9179, 77.6904),
    (12.9174, 77.6895),
    (12.9177, 77.69),
    (12.9178, 77.6907),
    (12.9159, 77.692),
    (12.9182, 77.691),
    (12.9183, 77.6911),
    (12.9178, 77.6911),
    (12.9180, 77.6907),
    (12.9178, 77.6902),
    (12.9175, 77.6896),
    (12.9170, 77.6895),
    (12.9165, 77.6899),
    (12.9159, 77.6898),
    (12.9164, 77.69),
    (12.9171, 77.6895),
    (12.9156, 77.6899),
    (12.9162, 77.691),
    (12.9157, 77.6908),
    (12.9162, 77.6908),
    (12.9167, 77.6908),
    (12.9162, 77.6908),
    (12.9157, 77.6909),
    (12.9154, 77.6894),
    (12.9164, 77.6892),
    (12.9170, 77.6891)
]

new_nodes = []

for node in nodes:
    # nearest_node, dist = ox.get_nearest_node(G, (12.9148560, 77.6774713), method='euclidean', return_dist=True)
    nearest_node, dist = ox.get_nearest_node(G, node, return_dist=True)
    # nearest_node = ox.get_nearest_nodes(G, [node[0]], [node[1]])[0]
    print("#"*10)
    print("Co-ordinates:", node)
    print("Node:", nearest_node)
    print("Distance:", dist)
    print("#"*10)
    print()
    new_nodes.append(nearest_node)


print(new_nodes)
