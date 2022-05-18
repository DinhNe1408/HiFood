import numpy as np
import sqlite3
import matplotlib.pyplot as plt
import pandas as pd
from sklearn.neighbors import NearestNeighbors
from os.path import dirname, join,abspath
link = abspath("/data/data/com.example.bctn/databases/HiFood.db")
conn = sqlite3.connect(link, isolation_level=None,
                       detect_types=sqlite3.PARSE_COLNAMES)
db_df = pd.read_sql_query("SELECT A.IDTK, B.IDQA, AVG(C.SaoDG) AS SAODG " +
                "FROM TaiKhoan A,QuanAn B, DanhGia C, DonHang D "+
                "WHERE D.IDDH = C.IDDH AND D.IDTK = A.IDTK AND B.IDQA = D.IDQA GROUP BY A.IDTK, B.IDQA", conn)

df = db_df

df.info()
df = df.dropna()
df.info()
df['SAODG'] = df['SAODG'].astype(float)

from scipy.sparse import csr_matrix
columns = df[['IDTK', 'IDQA', 'SAODG']]
df_new = columns

ratingCount = (df_new. groupby(by=['IDQA'])['SAODG'].count().reset_index().rename(columns={'SAODG': 'totalRatingCount'})[['IDQA', 'totalRatingCount']])

rating_with_totalRatingCount = df_new.merge(ratingCount, left_on = 'IDQA', right_on = 'IDQA', how = 'left')

user_rating_pivot = rating_with_totalRatingCount.pivot_table(index='IDQA', columns='IDTK', values='SAODG').fillna(0)
user_rating_matrix = csr_matrix(user_rating_pivot.values)

def BCTN_HTGY(IDTK):
    model_knn = NearestNeighbors(metric='cosine', algorithm='brute')
    model_knn.fit(user_rating_matrix)
    query_index = 0
    for i in range(len(user_rating_pivot)):
        if ( user_rating_pivot.index[i] == IDTK ):
            query_index = i
            break

    distances, indices = model_knn.kneighbors(user_rating_pivot.iloc[query_index, :].values.reshape(1, -1), n_neighbors=9)
    array_best = []
    for i in range(0, len(distances.flatten())):
        if i == 0:
            print('Recommendations for {0}:\n'.format(user_rating_pivot.index[query_index], distances.flatten()[-i]))
        else:
            array_best.append(user_rating_pivot.index[indices.flatten()[-i]])
            print('{0}: {1}, with distance of {2}.'.format(i, user_rating_pivot.index[indices.flatten()[-i]], distances.flatten()[-i]))
    return array_best