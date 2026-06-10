colors = [
"White", 
"Light_Gray",
"Gray", 
"Black",
"Brown", 
"Red",
"Orange", 
"Yellow",
"Lime", 
"Green",
"Cyan", 
"Light_Blue",
"Blue", 
"Purple",
"Magenta", 
"Pink",
]
for i in colors:
	c = i.lower()
	print(f"""public static final Item {c.upper()}_WATER_BOTTLE = register("{c}_water_bottle", () -> {{return new colored_bottle("{c}");}});""")

for i in colors:
	c = i.upper()
	print(f"output.accept(ModItems.{c}_WATER_BOTTLE);")

for i in colors:
	a = i.lower()
	b = i.upper()
	print(f"""case "{a}":
	return ModItems.{b}_WATER_BOTTLE;""")

#path = "src/main/resources/assets/chou/"
#for i in colors:
#	c = i.lower()
#	with open(f"{path}/items/{c}_water_bottle.json", "w") as f:
#		f.write(
#f"""{{
#  "model": {{
#    "type": "minecraft:model",
#    "model": "chou:item/{c}_water_bottle"
#  }}
#}}
#""")
#	with open(f"{path}/models/item/{c}_water_bottle.json", "w") as f:
#		f.write(
#f"""{{
#	"parent": "minecraft:item/generated",
#	"textures": {{
#		"layer0": "chou:item/{c}_water_bottle"
#	}}
#}}
#""")

#for i in colors:
#	c = i.lower()
#	print(f""""iten.chou.{c}_water_bottle": "{i.replace('_', '')} Water Bottle",""")

for i in colors:
	c = i.lower()
	print(f""""{c}_colored_brush",""")